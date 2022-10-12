package org.compiere.model;

import java.util.Properties;

import org.adempiere.core.domains.models.X_AD_Tree_Favorite_Node;

public class MTreeFavoriteNode extends X_AD_Tree_Favorite_Node{

	private static final long serialVersionUID = -4823162681404075816L;
	
	public MTreeFavoriteNode(Properties ctx, int AD_Tree_Favorite_Node_ID,
			String trxName) {
		super(ctx, AD_Tree_Favorite_Node_ID, trxName);
	}
}
