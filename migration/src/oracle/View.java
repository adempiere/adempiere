/**
 * ADempiere contribution
 * Author: Karsten Thiemann, kthiemann@adempiere.org
 * Compiere/Adempiere migration script generation.
*/

package oracle;

public class View {
	
	private String name;
	
	private String text;

	public View(String name, String text) {
		super();
		this.name = name;
		this.text = text;
	}

	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}
	
	public String getCreateStatement(){
		return "\nCREATE OR REPLACE FORCE VIEW " + name + "\nAS " +
		text + ";";
	}

}
