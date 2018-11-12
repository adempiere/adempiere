package org.compiere.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;

import org.adempiere.exceptions.AdempiereException;
import org.compiere.util.DB;
import org.compiere.util.Env;

public class MTreeFavorite extends X_AD_Tree_Favorite
{

	private static final long		serialVersionUID	= 4367834596370363558L;
	private ArrayList<MTreeNode>	m_buffer			= new ArrayList<MTreeNode>();
	private MTreeNode				mroot				= null;

	public MTreeFavorite(Properties ctx, ResultSet rs, String trxName)
	{
		super(ctx, rs, trxName);
	}

	public MTreeFavorite(Properties ctx, int AD_Tree_Favorite_ID, String trxName)
	{
		super(ctx, AD_Tree_Favorite_ID, trxName);

		if (AD_Tree_Favorite_ID != 0)
			loadNode(AD_Tree_Favorite_ID);
	}

	/**
	 * Geting Tree ID for a specific User & Role Wise
	 * 
	 * @param roleid
	 * @param userid
	 * @return
	 */
	public int getTreeID(int roleid, int userid, int clientid)
	{
		int AD_Tree_ID = -1;
		String Query = "SELECT ad_tree_favorite_id FROM ad_tree_favorite  WHERE ad_role_id = ? AND ad_user_id = ? AND ad_client_id = ?";
		Object[] params = { roleid, userid, clientid };
		AD_Tree_ID = DB.getSQLValue(null, Query, params);
		return AD_Tree_ID;
	}

	public MTreeNode getRoot()
	{
		return mroot;
	}

	/**
	 * Load Node Into Tree
	 * 
	 * @param AD_Tree_Favorite_ID
	 */
	public void loadNode(int AD_Tree_Favorite_ID)
	{
		String displayQuery = "SELECT fn.ad_tree_favorite_node_id, fn.parent_id, fn.seqno, fn.nodename,  fn.issummary, fn.ad_menu_id, iscollapsible,"
				+ " coalesce(t.name, m.name) as menuTrl "
				+ " FROM ad_tree_favorite_node fn  "
				+ " LEFT JOIN AD_Menu m on (m.AD_Menu_ID = fn.ad_menu_ID)"
				+ " LEFT JOIN AD_Menu_Trl t ON(t.AD_Menu_ID = fn.AD_Menu_ID AND t.AD_Language='" + Env.getAD_Language(p_ctx) + "')"
				+ " WHERE ad_tree_favorite_id = ? "				
				+ " ORDER BY COALESCE(parent_id,-1), seqno, nodename";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			pstmt = DB.prepareStatement(displayQuery, get_TrxName());
			pstmt.setInt(1, AD_Tree_Favorite_ID);
			rs = pstmt.executeQuery();
			mroot = new MTreeNode(0, 0, "User Favourite Root", "User Favourite Root", 0, true, 0, null, false);
			
			while (rs.next())
			{
				int NodeID = rs.getInt(1);
				int parentID = rs.getInt(2);
				int seqno = rs.getInt(3);					
				String name = rs.getString(4);
				boolean isSummary = (rs.getString(5).equals("Y"));
				boolean isCollapsible = rs.getString("iscollapsible").equals("Y");
				String img = null;
				int menuID = 0;
				if (!isSummary)
				{
					menuID = rs.getInt(6);
					MMenu mMenu = new MMenu(Env.getCtx(), menuID, null);	
					if (Env.getLanguage(Env.getCtx()).isBaseLanguage()) {
						name = mMenu.getName();
					}
					else {
						name = rs.getString(8);
					}
					img = mMenu.getAction();
				}
				if (AD_Tree_Favorite_ID == 0 && (parentID == 0 || NodeID == 0))
					;
				else
					addToTree(NodeID, parentID, seqno, name, isSummary, menuID, img, isCollapsible);
			}
		}
		catch (SQLException e)
		{
			log.log(Level.SEVERE, displayQuery.toString(), e);
			throw new AdempiereException(e);
		}
		finally
		{
			DB.close(rs);
			DB.close(pstmt);
			rs = null;
			pstmt = null;
		}
	}

	/**
	 * Adding Node Into Tree
	 * 
	 * @param favNodeID
	 * @param parentID
	 * @param seqno
	 * @param name
	 * @param isSummary
	 * @param menuID
	 * @param img
	 * @param isCollapsible 
	 */
	private void addToTree(int favNodeID, int parentID, int seqno, String name, boolean isSummary, int menuID,
			String img, boolean isCollapsible)
	{
		MTreeNode child = new MTreeNode(favNodeID, seqno, name, name, parentID, isSummary, menuID, img, isCollapsible);

		MTreeNode parent = null;
		if (mroot != null)
			parent = mroot.findNode(parentID);
		// Parent found
		if (parent != null && parent.getAllowsChildren())
		{
			parent.add(child);
			if (m_buffer.size() > 0)
				checkBuffer(child);
		}
		else
			m_buffer.add(child);
	} // addToTree

	/**
	 * Check the buffer for nodes which have newNode as Parents
	 * 
	 * @param newNode new node
	 */
	private void checkBuffer(MTreeNode child)
	{
		// Ability to add nodes
		if (!child.isSummary() || !child.getAllowsChildren())
			return;
		for (int i = 0; i < m_buffer.size(); i++)
		{
			MTreeNode node = (MTreeNode) m_buffer.get(i);
			if (node.getParent_ID() == child.getParent_ID())
			{
				try
				{
					child.add(node);
				}
				catch (Exception e)
				{
					log.severe("Adding " + node.getName() + " to " + child.getName() + ": " + e.getMessage());
				}
				m_buffer.remove(i);
				i--;
			}
			else if (node.getParent_ID() == child.getNode_ID())
			{
				try
				{
					child.add(node);
				}
				catch (Exception e)
				{
					log.severe("Adding " + node.getName() + " to " + child.getName() + ": " + e.getMessage());
				}
				m_buffer.remove(i);
				i--;
			}
		}
	}

	/**
	 * Check the Menu ID is Present on specified Parent id or not
	 * 
	 * @param mID
	 * @param parent_ID
	 * @param treeFavoriteID
	 * @return
	 */
	public boolean isMenuAvailable(int mID, int parent_ID, int treeFavoriteID)
	{
		String Query = "SELECT ad_menu_id FROM ad_tree_favorite_node "
				+ " WHERE ad_tree_favorite_id = ? AND parent_id = ? AND ad_menu_id IS NOT NULL";
		boolean flag = false;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try
		{
			stmt = DB.prepareStatement(Query, null);
			stmt.setInt(1, treeFavoriteID);
			stmt.setInt(2, parent_ID);
			rs = stmt.executeQuery();
			while (rs.next())
			{
				if (rs.getInt(1) == mID)
					flag = true;
			}
		}
		catch (SQLException e)
		{
			throw new AdempiereException(e);
		}
		finally
		{
			DB.close(rs);
			DB.close(stmt);
			rs = null;
			stmt = null;
		}
		return flag;
	}
}