/**
 * Copyright (C) 2003-2017, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.compiere.model;

import org.compiere.util.CLogger;

import java.sql.ResultSet;
import java.util.List;
import java.util.Properties;

/**
 * Tree Node 1
 */
public class MTree_NodeU1 extends X_AD_TreeNodeU1 {
    /**
     *
     */
    private static final long serialVersionUID = -1641160984037417838L;

    /**
     * Get Tree
     *
     * @param ctx     context
     * @param treeId  tree
     * @param trxName transaction
     * @return list of nodes
     */
    public static List<MTree_NodeU1> getTree(Properties ctx, int treeId, String trxName) {
        return new Query(ctx, Table_Name, "AD_Tree_ID=?", trxName)
                .setClient_ID()
                .setParameters(treeId)
                .setOrderBy("Node_ID")
                .list();
    }

    /**
     * Get Tree Node
     *
     * @param tree
     * @param nodeId
     * @return
     */
    public static MTree_NodeU1 get(MTree tree, int nodeId) {
        return new Query(tree.getCtx(), Table_Name, "AD_Tree_ID=? AND Node_ID=?", tree.get_TrxName())
                .setClient_ID()
                .setParameters(tree.getAD_Tree_ID(), nodeId)
                .first();
    }

    /**
     * Static Logger
     */
    private static CLogger logger = CLogger.getCLogger(MTree_NodeU1.class);

    /**
     * Load Constructor
     *
     * @param ctx     context
     * @param rs      result set
     * @param trxName transaction
     */
    public MTree_NodeU1(Properties ctx, ResultSet rs, String trxName) {
        super(ctx, rs, trxName);
    }

    /**
     * Full Constructor
     *
     * @param tree   tree
     * @param nodeId node
     */
    public MTree_NodeU1(MTree tree, int nodeId) {
        super(tree.getCtx(), 0, tree.get_TrxName());
        setClientOrg(tree);
        setAD_Tree_ID(tree.getAD_Tree_ID());
        setNode_ID(nodeId);
        setParent_ID(0);
        setSeqNo(0);
    }

    /**
     * setParent_ID overwrite as Tree's need to allow 0 parents
     *
     * @param parentId
     */
    public void setParent_ID(int parentId) {
        set_Value("Parent_ID", new Integer(parentId));
    }
}
