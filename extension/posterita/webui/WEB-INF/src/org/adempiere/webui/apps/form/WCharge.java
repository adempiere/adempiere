/******************************************************************************
 * Product: Adempiere ERP & CRM Smart Business Solution                       *
 * Copyright (C) 1999-2006 ComPiere, Inc. All Rights Reserved.                *
 * This program is free software; you can redistribute it and/or modify it    *
 * under the terms version 2 of the GNU General Public License as published   *
 * by the Free Software Foundation. This program is distributed in the hope   *
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the implied *
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.           *
 * See the GNU General Public License for more details.                       *
 * You should have received a copy of the GNU General Public License along    *
 * with this program; if not, write to the Free Software Foundation, Inc.,    *
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA.                     *
 * For the text or an alternative of this public license, you may reach us    *
 * ComPiere, Inc., 2620 Augustine Dr. #245, Santa Clara, CA 95054, USA        *
 * or via info@compiere.org or http://www.compiere.org/license.html           *
 *****************************************************************************/

/**
 * 2007, Modified by Posterita Ltd.
 */

package org.adempiere.webui.apps.form;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;

import org.adempiere.webui.component.Button;
import org.adempiere.webui.component.Checkbox;
import org.adempiere.webui.component.Column;
import org.adempiere.webui.component.Columns;
import org.adempiere.webui.component.Grid;
import org.adempiere.webui.component.Label;
import org.adempiere.webui.component.ListModelTable;
import org.adempiere.webui.component.Panel;
import org.adempiere.webui.component.Row;
import org.adempiere.webui.component.Rows;
import org.adempiere.webui.component.Textbox;
import org.adempiere.webui.component.WConfirmPanel;
import org.adempiere.webui.component.WListbox;
import org.adempiere.webui.panel.ADForm;
import org.adempiere.webui.session.SessionManager;
import org.adempiere.webui.window.FDialog;
import org.compiere.model.MAccount;
import org.compiere.model.MAcctSchema;
import org.compiere.model.MCharge;
import org.compiere.model.MElementValue;
import org.compiere.util.CLogger;
import org.compiere.util.DB;
import org.compiere.util.Env;
import org.compiere.util.KeyNamePair;
import org.compiere.util.Msg;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;

/**
 * This class represents the Custom Form for generating charges
 * from natural accounts.
 *
 * The form is comprised of two parts.
 * The upper portion can be used to create new charges using the general charge accounts.
 * The lower portion can be used to create charges based on the natural account.
 *
 * @author Andrew Kimball
 *
 */
public class WCharge extends ADForm implements EventListener
{
    /** Unique identifier. */
    private static final long serialVersionUID = 1L;
    /** AD_Message for "Create". */
    private static final String AD_MESSAGE_CREATE = "Create";
    /** Logger.          */
    private static CLogger log = CLogger.getCLogger(WCharge.class);

    /** Account Element identifier.     */
    private int m_elementId = 0;
    /** Account Schema identifier.       */
    private int m_accountSchemaId = 0;
    /** Default Charge Tax Category. */
    private int m_taxCategoryId = 0;
    /** Identifier for the client. */
    private int m_clientId = 0;
    /** Identifier for the organisation. */
    private int m_organisationId = 0;
    /** Accounting schema model. */
    private MAcctSchema  m_acctSchema = null;

    /** Panel for holding other panels. */
    private Panel m_pnlMain = new Panel();

    // new panel
    /** Grid for components for creating a new charge account. */
    private Grid m_grdNew = new Grid();
    /** Title of new charge account grid. */
    private Column m_clmNewTitle = new Column();
    /** Value (key) field label. */
    private Label m_lblValue = new Label();
    /** Field for specifying value (key) of new account. */
    private Textbox m_txbValueField = new Textbox();
    /** Checkbox for specifying whether or not the new account is an expense account. */
    private Checkbox m_chbIsExpense = new Checkbox();
    /** Name field label. */
    private Label m_lblName = new Label();
    /** Field for specifying name of new account. */
    private Textbox m_txbNameField = new Textbox();
    /** Button to create new account. */
    private Button m_btnNew = new Button();

    // account panel
    /** Grid for components for creating a charge form a selected account. **/
    private Grid m_grdAccount = new Grid();
    /** Title of account grid. */
    private Column m_clmAccountTitle = new Column();
    /** Button to create charge from selected account. */
    private Button m_btnAccount = new Button();
    /** Table to hold data of accounts. */
    private WListbox m_tblData = new WListbox();

    /** confirmation panel. */
    private WConfirmPanel m_pnlConfirm = new WConfirmPanel();
    /** Confirmation Grid. */
    private Grid m_grdConfirm = new Grid();

    /** Enumeration of column names and indices. */
    private enum EColumn
    {
        /** Select column to record whether the account is selected. */
        SELECT(0, "Select"),
        /** Value column to hold the account key. */
        VALUE(1, "Value"),
        /** Name column to hold the account name. */
        NAME(2, "Name"),
        /** Expense column to indicate whether or not the account is an expense account. */
        EXPENSE(3, "Expense");

        /** The column's index. */
        private final int m_index;
        /** The column's name. */
        private final String m_title;

        /**
         * Constructor.
         *
         * @param index index of the column
         * @param title name of the column
         */
        EColumn(int index, String title)
        {
            m_index = index;
            m_title = title;
        }

        /**
         * Gets the index of the column.
         *
         *  @return the column index.
         */
        public int index()
        {
            return m_index;
        }

        /**
         * Gets the name of the column.
         *
         * @return the column's name
         */
        public String title()
        {
            return m_title;
        }

        /**
         * Gets the number of columns.
         *
         * @return the number of columns.
         */
        public static int count()
        {
            return values().length;
        }

    }

    /**
     * Default constructor.
     */
    public WCharge()
    {
        super();
    }


    /**
     * Initialises the panel.
     *
     * @param adFormId  The Adempiere identifier for the form
     * @param name      The name of the form
     */
    public void init(int adFormId, String name)
    {
        super.init(adFormId, name);
        log.info("");
        try
        {
            staticInitialise();
            dynamicInitialise();
            this.appendChild(m_pnlMain);
            //this.appendChild(confirmPanel);
        }
        catch(Exception e)
        {
            log.log(Level.SEVERE, "", e);
        }

        return;
    }


    /**
     * Initialises the static components of the form.
     */
    private void staticInitialise()
    {
        createNewChargePanel();
        createAccountPanel();
        createConfirmPanel();
        // TODO
        m_pnlMain.appendChild(m_grdNew);
        m_pnlMain.appendChild(m_grdAccount);
        m_pnlMain.appendChild(m_grdConfirm);
        return;
    }

    /**
     * Creates the account panel.
     *
     * The account panel contains:
     * <li>a table detailing all accounts
     * <li>a button for creating charges for selected accounts
     */
    private void createAccountPanel()
    {
        Row topRow = new Row();
        Row bottomRow = new Row();
        Rows rows = new Rows();
        Columns header = new Columns();

        // header
        m_clmAccountTitle.setLabel(Msg.getMsg(Env.getCtx(), "ChargeFromAccount"));
        header.appendChild(m_clmAccountTitle);

        // top row
        m_tblData.setRows(20);
        topRow.appendChild(m_tblData);
        rows.appendChild(topRow);

        // bottom row
        bottomRow.setAlign("right");
        m_btnAccount.setLabel(Msg.getMsg(Env.getCtx(), AD_MESSAGE_CREATE));
        m_btnAccount.addEventListener(Events.ON_CLICK, this);
        bottomRow.appendChild(m_btnAccount);
        rows.appendChild(bottomRow);

        // put it all together
        m_grdAccount.appendChild(header);
        m_grdAccount.appendChild(rows);

        return;
    }

    /**
     * Creates the New Charge panel.
     *
     * The New Charge panel is used to specify the name and key of an account
     * and whether or not the account is a charge account.
     */
    private void createNewChargePanel()
    {
        final int nameFieldColumns = 20;
        final int valueFieldColumns = 10;
        Row topRow = new Row();
        Row bottomRow = new Row();
        Rows rows = new Rows();
        Columns header = new Columns();

        // header
        m_clmNewTitle.setLabel(Msg.getMsg(Env.getCtx(), "ChargeNewAccount"));
        header.appendChild(m_clmNewTitle);

        // top row
        m_lblValue.setValue(Msg.translate(Env.getCtx(), EColumn.VALUE.title()));
        m_txbValueField.setCols(valueFieldColumns);
        m_chbIsExpense.setChecked(true);
        m_chbIsExpense.setLabel(Msg.getMsg(Env.getCtx(), EColumn.EXPENSE.title()));
        topRow.appendChild(m_lblValue);
        topRow.appendChild(m_txbValueField);
        topRow.appendChild(m_chbIsExpense);
        rows.appendChild(topRow);

        // bottom row
        m_lblName.setValue(Msg.translate(Env.getCtx(), EColumn.NAME.title()));
        m_txbNameField.setCols(nameFieldColumns);
        m_btnNew.setLabel(Msg.getMsg(Env.getCtx(), AD_MESSAGE_CREATE));
        m_btnNew.addEventListener(Events.ON_CLICK, this);
        bottomRow.appendChild(m_lblName);
        bottomRow.appendChild(m_txbNameField);
        bottomRow.appendChild(m_btnNew);
        rows.appendChild(bottomRow);

        // put it all together
        m_grdNew.appendChild(header);
        m_grdNew.appendChild(rows);

        return;
    }


    /**
     *  Initialises the dynamic components of the form.
     *  <li>Gets defaults for primary AcctSchema
     *  <li>Creates Table with Accounts
     */
    private void dynamicInitialise()
    {
        String sql;
        findChargeElementID();

        if (m_elementId == 0)
        {
            return;
        }

        //  Table
        Vector<Vector<Object>> data = new Vector<Vector<Object>>();
        sql = "SELECT C_ElementValue_ID,Value, Name, AccountType "
            + "FROM C_ElementValue "
            + "WHERE AccountType IN ('R','E')"
            + " AND IsSummary='N'"
            + " AND C_Element_ID=? "
            + "ORDER BY 2";
        try
        {
            PreparedStatement pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, m_elementId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                Vector<Object> line = createDataLine(rs);
                data.add(line);
            }
            rs.close();
            pstmt.close();
        }
        catch (SQLException exception)
        {
            log.log(Level.SEVERE, sql, exception);
        }
        //  Header Info
        Vector<String> columnNames = getColumnNames();

        //  Set Model
        ListModelTable model = new ListModelTable(data);
        m_tblData.setData(model, columnNames);
        //
        m_tblData.setColumnClass(EColumn.SELECT.index(), Boolean.class, false);      //  0-Selection
        m_tblData.setColumnClass(EColumn.VALUE.index(), String.class, true);        //  1-Value
        m_tblData.setColumnClass(EColumn.NAME.index(), String.class, true);        //  2-Name
        m_tblData.setColumnClass(EColumn.EXPENSE.index(), Boolean.class, true);       //  3-Expense
        //  Table UI
        //m_tblData.autoSize();

        //  Other Defaults
        m_clientId = Env.getAD_Client_ID(Env.getCtx());
        m_organisationId = Env.getAD_Org_ID(Env.getCtx());

        //  TaxCategory
        findTaxCategoryID();

        return;
    }   //  dynInit


    /**
     * Finds the identifier for the tax category for the client.
     */
    private void findTaxCategoryID()
    {
        final String sql = "SELECT C_TaxCategory_ID FROM C_TaxCategory "
                + "WHERE IsDefault='Y' AND AD_Client_ID=?";
        m_taxCategoryId = 0;
        try
        {
            PreparedStatement pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, m_clientId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                m_taxCategoryId = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        }
        catch (SQLException exception)
        {
            log.log(Level.SEVERE, sql, exception);
        }

        return;
    }


    /**
     * Gets a vector of column names.
     * The column names are used as column headings int he table.
     * @return a vector of column names.
     */
    private Vector<String> getColumnNames()
    {
        Vector<String> columnNames = new Vector<String>(EColumn.count());

        columnNames.add(Msg.getMsg(Env.getCtx(), EColumn.SELECT.title()));
        columnNames.add(Msg.translate(Env.getCtx(), EColumn.VALUE.title()));
        columnNames.add(Msg.translate(Env.getCtx(), EColumn.NAME.title()));
        columnNames.add(Msg.getMsg(Env.getCtx(), EColumn.EXPENSE.title()));

        return columnNames;
    }

    /**
     * Creates a data line from the given <code>ResultSet</code>.
     *
     * @param rs    result set containing details of an account.
     * @return a vector containing details of an account.
     * @throws SQLException if a database access error occurred
     */
    private Vector<Object> createDataLine(ResultSet rs) throws SQLException
    {
        final int noFields = EColumn.count();
        final int valueIdIndex = 1;
        final int valueIndex = 2;
        final int nameIndex = 3;
        final int expenseIndex = 4;
        final String expenseType = "E";
        boolean isExpenseType;
        Vector<Object> line = new Vector<Object>(noFields);

        // 0-Selection
        line.add(new Boolean(false));

        // 1-Value
        KeyNamePair pp = new KeyNamePair(rs.getInt(valueIdIndex),
                rs.getString(valueIndex));
        line.add(pp);

        // 2-Name
        line.add(rs.getString(nameIndex));

        // 3-Expense
        isExpenseType = rs.getString(expenseIndex).equals(expenseType);
        line.add(new Boolean(isExpenseType));

        return line;
    }


    /**
     * Finds the Element Identifier for the current charge.
     *
     */
    private void findChargeElementID()
    {
        m_accountSchemaId = Env.getContextAsInt(Env.getCtx(), "$C_AcctSchema_ID");
        //  get Element
        String sql = "SELECT C_Element_ID "
            + "FROM C_AcctSchema_Element "
            + "WHERE ElementType='AC' AND C_AcctSchema_ID=?";

        try
        {
            PreparedStatement pstmt = DB.prepareStatement(sql, null);
            pstmt.setInt(1, m_accountSchemaId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next())
            {
                m_elementId = rs.getInt(1);
            }
            rs.close();
            pstmt.close();
        }
        catch (SQLException exception)
        {
            log.log(Level.SEVERE, sql, exception);
        }
    }

    /**
     *  Event Listener.
     *
     *  @param event event that has been fired.
     */
    public void onEvent(Event event)
    {
        log.info(event.getName());
        //
        if (event.getName().equals(WConfirmPanel.A_OK) || m_elementId == 0)
        {
            close();
        }
        //  new Account
        else if (event.getTarget().equals(m_btnNew))
        {
            createNew();
        }
        else if (event.getTarget().equals(m_btnAccount))
        {
            createAccount();
        }

        return;
    }

    /**
     *  Create new Chargeable Account.
     */
    private void createNew()
    {
        final String backgroundColorStyle = "background-color:";
        String value;
        String name;

        log.config("");
        //  Get Input
        value = m_txbValueField.getValue();
        if (value.length() == 0)
        {
/*            m_txbValueField.setStyle(backgroundColorStyle
                    + ZkCssHelper.createHexColorString(AdempierePLAF.getFieldBackground_Error()));*/
            return;
        }

        name = m_txbNameField.getText();
        if (name.length() == 0)
        {
/*            m_txbNameField.setStyle(backgroundColorStyle
                    + ZkCssHelper.createHexColorString(AdempierePLAF.getFieldBackground_Error()));*/
            return;
        }

        //  Create Element
        int elementValueId = createElementValue (value, name, m_chbIsExpense.isChecked());
        if (elementValueId == 0)
        {
            FDialog.error(m_windowNo, this, "ChargeNotCreated", name);
            return;
        }
        //  Create Charge
        int chargeId = createCharge(name, elementValueId);
        if (chargeId == 0)
        {
            FDialog.error(m_windowNo, this, "ChargeNotCreated", name);
            return;
        }
        FDialog.info(m_windowNo, this, "ChargeCreated", name);
    }   //  createNew



    /**
     *  Create Element Value for primary Account Schema.
     *  @param value            account key
     *  @param name             account name
     *  @param isExpenseType    is expense account
     *  @return element value identifier
     */
    private int createElementValue (String value, String name, boolean isExpenseType)
    {
        MElementValue elementValue;

        log.config(name);
        //
        elementValue = new MElementValue(Env.getCtx(),
                value,
                name,
                null,
                isExpenseType ? MElementValue.ACCOUNTTYPE_Expense
                        : MElementValue.ACCOUNTTYPE_Revenue,
                        MElementValue.ACCOUNTSIGN_Natural,
                false,
                false,
                null);

        elementValue.setAD_Org_ID(m_organisationId);
        if (!elementValue.save())
        {
            log.log(Level.WARNING, "C_ElementValue_ID not created");
        }

        return elementValue.getC_ElementValue_ID();
    }   //  create_ElementValue

    /**
     *  Create Charge and account entries for primary Account Schema.
     *
     *  @param name             charge name
     *  @param elementValueId   element value identifier
     *  @return charge identifier, or 0 if no charge created.
     */
    private int createCharge(String name, int elementValueId)
    {
        MCharge charge;
        MAccount account;

        log.config(name + " - ");
        // Charge
        charge = new MCharge(Env.getCtx(), 0, null);
        charge.setName(name);
        charge.setC_TaxCategory_ID(m_taxCategoryId);
        if (!charge.save())
        {
            log.log(Level.SEVERE, name + " not created");
            return 0;
        }

        refreshAccountSchema();
        if (!isAccountSchemaValid())
        {
            return 0;
        }

        //  Target Account
        account = getAccount(elementValueId, charge);
        if (account == null)
        {
            return 0;
        }

        updateAccount(charge, account);

        return charge.getC_Charge_ID();
    }   //  createCharge


    /**
     * Updates the charge account details.
     * @param charge    the charge
     * @param account   the account
     */
    private void updateAccount(MCharge charge, MAccount account)
    {
        StringBuffer sql = createUpdateAccountSql(charge, account);
        //
        int noAffectedRows = DB.executeUpdate(sql.toString(), null);
        if (noAffectedRows != 1)
        {
            log.log(Level.SEVERE, "Update #" + noAffectedRows + "\n" + sql.toString());
        }

        return;
    }


    /**
     * Queries whether the current account scheme is valid.
     * @return false if the current account is <code>null</code> or
     *         its identifier is 0 (zero).
     */
    private boolean isAccountSchemaValid()
    {
        if (m_acctSchema  == null)
        {
            return false;
        }
        else if (m_acctSchema.getC_AcctSchema_ID() == 0)
        {
            return false;
        }

        return true;
    }


    /**
     * Creates the SQL statement for updating the account and charge.
     *
     * @param charge    charge
     * @param account      account
     * @return the SQL DML statement for updating the specified account and charge.
     */
    private StringBuffer createUpdateAccountSql(MCharge charge, MAccount account)
    {
        StringBuffer sql = new StringBuffer("UPDATE C_Charge_Acct ");
        sql.append("SET CH_Expense_Acct=").append(account.getC_ValidCombination_ID());
        sql.append(", CH_Revenue_Acct=").append(account.getC_ValidCombination_ID());
        sql.append(" WHERE C_Charge_ID=").append(charge.getC_Charge_ID());
        sql.append(" AND C_AcctSchema_ID=").append(m_accountSchemaId);

        return sql;
    }


    /**
     * Refreshes the current account schema.
     *
     */
    private void refreshAccountSchema()
    {
        //  Get AcctSchama
        if (m_acctSchema == null)
        {
            m_acctSchema = new MAcctSchema(Env.getCtx(), m_accountSchemaId, null);
        }

        return;
    }


    /**
     * Gets the account for the specified charge and element value.
     * The account is created if it doesn't already exist.
     * @param elementValueId    identifier for the element value
     * @param charge            charge
     * @return the account
     */
    private MAccount getAccount(int elementValueId, MCharge charge)
    {
        MAccount defaultAccount = MAccount.getDefault(m_acctSchema, true); //  optional null
        MAccount account = MAccount.get(Env.getCtx(),
            charge.getAD_Client_ID(),
            charge.getAD_Org_ID(),
            m_acctSchema.getC_AcctSchema_ID(),
            elementValueId,
            defaultAccount.getC_SubAcct_ID(),
            defaultAccount.getM_Product_ID(),
            defaultAccount.getC_BPartner_ID(),
            defaultAccount.getAD_OrgTrx_ID(),
            defaultAccount.getC_LocFrom_ID(),
            defaultAccount.getC_LocTo_ID(),
            defaultAccount.getC_SalesRegion_ID(),
            defaultAccount.getC_Project_ID(),
            defaultAccount.getC_Campaign_ID(),
            defaultAccount.getC_Activity_ID(),
            defaultAccount.getUser1_ID(),
            defaultAccount.getUser2_ID(),
            defaultAccount.getUserElement1_ID(),
            defaultAccount.getUserElement2_ID());

        return account;
    }


    /**
     *  Creates Charges from Accounts.
     *  Charges are created for the selected accounts.
     *  The selection is cleared upon completion.
     */
    private void createAccount()
    {
        StringBuffer listCreated = new StringBuffer();
        StringBuffer listRejected = new StringBuffer();

        log.config("");

        int noCharges = getNoCharges();

        for (int chargeIndex = 0; chargeIndex < noCharges; chargeIndex++)
        {
            if (isRowSelected(chargeIndex))
            {
                String name = getChargeName(chargeIndex);
                int chargeId = createCharge(chargeIndex);
                if (chargeId == 0)
                {
                    appendName(listRejected, name);
                }
                else
                {
                    appendName(listCreated, name);
                }
                setRowUnselected(chargeIndex);
            }
        }
        if (listCreated.length() > 0)
        {
            FDialog.info(m_windowNo, this, "ChargeCreated", listCreated.toString());
        }
        if (listRejected.length() > 0)
        {
            FDialog.error(m_windowNo, this, "ChargeNotCreated", listRejected.toString());
        }

        return;
    }   //  createAccount


    /**
     * Gets the number of charges in the table.
     * @return the number of charges in the table.
     */
    private int getNoCharges()
    {
        int noCharges = m_tblData.getRowCount();

        return noCharges;
    }

    /**
     * Creates a charge for specified table row.
     *
     * @param rowIndex  index of the row for which a charge is to be created.
     * @return the charge identifier.
     */
    private int createCharge(int rowIndex)
    {
        KeyNamePair pp = (KeyNamePair)m_tblData.getValueAt(rowIndex, EColumn.VALUE.index());
        int elementValueId = pp.getKey();
        String name = getChargeName(rowIndex);
        int chargeID = createCharge(name, elementValueId);

        return chargeID;
    }


    /**
     * Gets the name for a specified table row.
     * @param rowIndex  the table row for which to get the name.
     * @return the charge name.
     */
    private String getChargeName(int rowIndex)
    {
        String name = (String)m_tblData.getValueAt(rowIndex, EColumn.NAME.index());

        return name;
    }

    /**
     * Appends the <code>name</code> to the <code>nameList</code>.
     * @param nameList  a list of names
     * @param name      the name to append
     */
    private void appendName(StringBuffer nameList, String name)
    {
        if (nameList.length() > 0)
        {
            nameList.append(", ");
        }
        nameList.append(name);

        return;
    }


    /**
     * Sets a row at <code>rowIndex</code> as unselected.
     * @param rowIndex index of the row to deselect.
     */
    private void setRowUnselected(int rowIndex)
    {
        ListModelTable model = m_tblData.getModel();
        model.setDataAt(Boolean.valueOf(false), rowIndex, EColumn.SELECT.index());

        return;
    }

    /**
     * Queries whether a row is selected.
     * @param rowIndex  index of the row to query.
     * @return true if the row is selected, false otherwise.
     */
    private boolean isRowSelected(int rowIndex)
    {
        ListModelTable model = m_tblData.getModel();
        Boolean isSelected = (Boolean)model.getDataAt(rowIndex, EColumn.SELECT.index());

        return isSelected.booleanValue();
    }

    /**
     *  Create Confirmation Panel with OK Button.
     */
    public void createConfirmPanel()
    {
        Rows rows = new Rows();
        Row row = new Row();
        m_pnlConfirm.addEventListener(this);
        row.appendChild(m_pnlConfirm);
        rows.appendChild(row);
        m_grdConfirm.appendChild(rows);

        return;
    }   //  ConfirmPanel


    public void close()
    {
        SessionManager.getAppDesktop().removeWindow();
    }
}


