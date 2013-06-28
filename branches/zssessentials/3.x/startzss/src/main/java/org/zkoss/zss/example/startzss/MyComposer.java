package org.zkoss.zss.example.startzss;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zss.ui.Spreadsheet;

public class MyComposer extends SelectorComposer<Component> {

	@Wire("spreadsheet")
	Spreadsheet spreadsheet;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		//initialize stuff here
	}
}
