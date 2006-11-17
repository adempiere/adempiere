/**
 *  Check Tree (Nodes)
 *  (Run manually!)
 *  (** Not tested as database here is clean, so back up first **)
 *
 *  $Id: AD_TreeNode_Check.sql,v 1.1 2006/04/21 17:51:58 jjanke Exp $
 *

SELECT AD_Tree_ID, Node_ID 
FROM AD_TreeNode
GROUP BY AD_Tree_ID, Node_ID HAVING COUNT(*) > 1
/
SELECT AD_Tree_ID, Node_ID 
FROM AD_TreeNodeMM
GROUP BY AD_Tree_ID, Node_ID HAVING COUNT(*) > 1
/
SELECT AD_Tree_ID, Node_ID 
FROM AD_TreeNodePR
GROUP BY AD_Tree_ID, Node_ID HAVING COUNT(*) > 1
/
SELECT AD_Tree_ID, Node_ID 
FROM AD_TreeNodeBP
GROUP BY AD_Tree_ID, Node_ID HAVING COUNT(*) > 1
/

--  Need to be modofied for the different AD_TreeNode tables
DELETE AD_TreeNode tn
WHERE (AD_Tree_ID, Node_ID) IN
    (SELECT AD_Tree_ID, Node_ID FROM AD_TreeNode
    GROUP BY AD_Tree_ID, Node_ID HAVING COUNT(*) > 1)
AND ROWID=(SELECT ROWID FROM AD_TreeNode xx 
    WHERE tn.AD_Tree_ID=xx.AD_Tree_ID AND tn.Node_ID=xx.Node_ID AND ROWNUM=1)
/

--  Need to be modofied for the different AD_TreeNode tables
ALTER TABLE AD_TREENODE
    ADD     CONSTRAINT AD_TREENODE_KEY
    PRIMARY KEY (AD_TREE_ID,NODE_ID)
        USING INDEX TABLESPACE INDX
/


SELECT * FROM AD_Menu
WHERE AD_Menu_ID NOT IN (SELECT Node_ID FROM AD_TreeNodeMM)

SELECT * 
DELETE FROM AD_TreeNodeMM 
WHERE Node_ID NOT IN (SELECT AD_Menu_ID FROM AD_Menu)

COMMIT