/**
 * Copyright (C) 2003-2022, e-Evolution Consultants S.A. , http://www.e-evolution.com
 * This program is free software, you can redistribute it and/or modify it
 * under the terms version 2 of the GNU General Public License as published
 * or (at your option) any later version.
 * by the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY, without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along
 * with this program, if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.
 * For the text or an alternative of this public license, you may reach us
 * or via info@adempiere.net or http://www.adempiere.net/license.html
 * Email: victor.perez@e-evolution.com, http://www.e-evolution.com , http://github.com/e-Evolution
 * Created by victor.perez@e-evolution.com , www.e-evolution.com
 */

package org.adempiere.process;

import org.adempiere.model.MBrowse;
import org.compiere.model.MEntityType;
import org.compiere.model.MForm;
import org.compiere.model.MMenu;
import org.compiere.model.MProcess;
import org.compiere.model.MTree;
import org.compiere.model.MTreeNode;
import org.compiere.model.MWindow;
import org.compiere.wf.MWorkflow;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Optional;

/**
 * Generated Process for (Synchronize Entity Type)
 *
 * @author victor.perez@e-evolution.com , www.e-evolution.com
 * @version Release 3.9.3
 */
public class SynchronizeEntityType extends SynchronizeEntityTypeAbstract {
    @Override
    protected void prepare() {
        super.prepare();
    }

    @Override
    protected String doIt() throws Exception {
        MTree treeMenu = new org.compiere.model.MTree(getCtx(), 10 , false, get_TrxName());
        Optional<MTreeNode> maybeTreeRoot = Optional.ofNullable(treeMenu.getRoot());
        maybeTreeRoot.ifPresent(this::synchronize);
        return "@Ok@";
    }

    private void synchronize(MTreeNode treeRoot) {
        Optional<MMenu> maybeMenu = Optional.ofNullable(MMenu.getFromId(getCtx(), getMenuId()));
        MEntityType entityType = new MEntityType(getCtx(), getEntityTypeId(), get_TrxName());
        maybeMenu.ifPresent(menu -> synchronize(treeRoot , menu, entityType));
    }

    private void synchronize(MTreeNode treeRoot, MMenu menu, MEntityType entityType) {
        Optional<MTreeNode> maybeTreeItem = Optional.ofNullable(treeRoot.findNode(menu.getAD_Menu_ID()));
        //Synchronize Menu Childs
        if (menu.isSummary()) {
            if (!menu.getEntityType().equals(entityType.getEntityType())) {
                menu.setEntityType(entityType.getEntityType());
                menu.saveEx();
            }
            maybeTreeItem.ifPresent(treeItem -> {
                Enumeration<?> children = treeItem.children();
                while (children.hasMoreElements()) {
                    MTreeNode childNode = (MTreeNode) children.nextElement();
                    Optional<MMenu> maybeChildMenu = Optional.ofNullable(MMenu.getFromId(getCtx(), childNode.getNode_ID()));
                    maybeChildMenu.ifPresent(menuChild -> synchronize(treeRoot, menuChild, entityType));
                }
            });
        } else {
            //Synchronize Window
            if (menu.getAD_Window_ID() > 0) {
                MWindow window = MWindow.get(getCtx(), menu.getAD_Window_ID());
                if (!window.getEntityType().equals(entityType.getEntityType())) {
                    window.setEntityType(entityType.getEntityType());
                    window.saveEx();
                }
                //Synchronize Tabs
                Arrays.asList(window.getTabs(true, get_TrxName())).forEach(tab -> {
                    if (!tab.getEntityType().equals(entityType.getEntityType())) {
                        tab.setEntityType(entityType.getEntityType());
                        tab.saveEx();
                    }
                    //Synchronize Fields
                    Arrays.asList(tab.getFields(true, get_TrxName())).forEach(field -> {
                        if (!field.getEntityType().equals(entityType.getEntityType())) {
                            field.setEntityType(entityType.getEntityType());
                            field.saveEx();
                        }
                    });
                });
            }
            if (menu.getAD_Form_ID() > 0) {
                //Synchronize From
                MForm form = new MForm(getCtx(), menu.getAD_Form_ID(), get_TrxName());
                if (!form.getEntityType().equals(entityType.getEntityType())) {
                    form.setEntityType(entityType.getEntityType());
                    form.saveEx();
                }
            }
            //Synchronize Process
            if (menu.getAD_Process_ID() > 0) {
                MProcess process = MProcess.get(getCtx(), menu.getAD_Process_ID());
                if (!process.getEntityType().equals(entityType.getEntityType())) {
                    process.setEntityType(entityType.getEntityType());
                    process.saveEx();
                }
                Arrays.asList(process.getParameters()).forEach(processPara -> {
                    if (!processPara.getEntityType().equals(entityType.getEntityType())) {
                        processPara.setEntityType(entityType.getEntityType());
                        processPara.saveEx();
                    }
                });
            }
            //Synchronize Browser
            if (menu.getAD_Browse_ID() > 0) {
                MBrowse browse = MBrowse.get(getCtx(), menu.getAD_Browse_ID());
                if (!browse.getEntityType().equals(entityType.getEntityType())) {
                    browse.setEntityType(entityType.getEntityType());
                    browse.saveEx();
                }
                browse.getFields().forEach(field -> {
                    if (!field.getEntityType().equals(entityType.getEntityType())) {
                        field.setEntityType(entityType.getEntityType());
                        field.saveEx();
                    }
                });
            }
            //Synchronize Workflow
            if (menu.getAD_Workflow_ID() > 0) {
                MWorkflow workflow = MWorkflow.get(getCtx(), menu.getAD_Workflow_ID());
                if (!workflow.getEntityType().equals(entityType.getEntityType())) {
                    workflow.setEntityType(entityType.getEntityType());
                    workflow.saveEx();
                }
                //Synchronize Node
                Arrays.asList(workflow.getNodes(true, getAD_Client_ID())).forEach(node -> {
                    if (!node.getEntityType().equals(entityType.getEntityType())) {
                        node.setEntityType(entityType.getEntityType());
                        node.saveEx();
                    }
                    //Synchronize Transition
                    Arrays.asList(node.getTransitions(getAD_Client_ID())).forEach(next -> {
                        if (!next.getEntityType().equals(entityType.getEntityType())) {
                            next.setEntityType(entityType.getEntityType());
                            next.saveEx();
                        }
                    });
                });
            }
        }
    }
}