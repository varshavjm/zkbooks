/* BookSheetComposer.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Nov 18, 2010 5:42:27 PM , Created by Sam
}}IS_NOTE

Copyright (C) 2009 Potix Corporation. All Rights Reserved.

*/
package org.zkoss.zss.essential;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zss.api.Ranges;
import org.zkoss.zss.api.model.Sheet;
import org.zkoss.zss.ui.Spreadsheet;
import org.zkoss.zss.ui.event.SheetSelectEvent;
import org.zkoss.zul.Label;

/**
 * Demonstrate how to change different sheet
 * @author Hawk
 *
 */
@SuppressWarnings("serial")
public class ProtectionComposer extends SelectorComposer<Component>{
	
	@Wire
	private Spreadsheet ss;
	@Wire
	private Label status;

	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		updateSheetProtectionStatus(ss.getSelectedSheet());
	}
	
	@Listen("onClick = #toggleProtection")
	public void toggleProtection(){
		Sheet selectedSheet = ss.getSelectedSheet();
		if (selectedSheet.isProtected()){
			Ranges.range(selectedSheet).protectSheet(null);
		}else{
			Ranges.range(selectedSheet).protectSheet("s");
		}
		updateSheetProtectionStatus(selectedSheet);
	}
	
	@Listen("onSheetSelect = #ss")
	public void selectSheet(SheetSelectEvent event) {
		updateSheetProtectionStatus(event.getSheet());
	}
	
	private void updateSheetProtectionStatus(Sheet sheet){
		status.setValue(Boolean.toString(sheet.isProtected()));
	}
}