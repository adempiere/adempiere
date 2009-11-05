/**
 * ADempiere contribution
 * Author: Karsten Thiemann, kthiemann@adempiere.org
 * Compiere/Adempiere migration script generation.
*/

package oracle;


public class RefListElement {
	private int id;

	private String value;

	private String name;

	public RefListElement(int id, String value, String name) {
		super();
		this.id = id;
		this.value = value;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUpdateString() {
		return "UPDATE AD_Ref_List SET value='" + value + "', name='" + name + "'"
				+ " WHERE AD_Ref_List_ID=" + id + ";";
	}

	public String getInsertString() {
		return ""; // TODO create insert string?
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof RefListElement)) {
			return false;
		} else {
			RefListElement comp = (RefListElement) object;
			return (comp.getId() == id && comp.getName().equals(name) && comp.getValue()
					.equals(value));
		}
	}

}
