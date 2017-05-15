package org.lbchild.window;

import java.util.List;

import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swtbot.eclipse.finder.matchers.WidgetMatcherFactory;
import org.eclipse.swtbot.swt.finder.SWTBot;
import org.eclipse.swtbot.swt.finder.finders.UIThreadRunnable;
import org.eclipse.swtbot.swt.finder.results.Result;
import org.eclipse.swtbot.swt.finder.widgets.AbstractSWTBot;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotCombo;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotTable;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotText;
import org.eclipse.swtbot.swt.finder.widgets.SWTBotToolbarButton;
import org.eclipse.ui.forms.widgets.Section;

public class DefaultSection extends AbstractSWTBot<Section> { 
	 
	private SWTBot bot; 
	 
	 /**
	  *  
	  * @param text 
	  */ 
	 public DefaultSection(String text) { 
	  this(getSection(text)); 
	 } 
	  
	 /**
	  *  
	  * @param section 
	  */ 
	 private DefaultSection(Section section) { 
	  super(section); 
	  bot = new SWTBot(section); 
	 } 
	  
	 /**
	  *  
	  * @return 
	  */ 
	 public SWTBotTable getTable() { 
	  return bot.table(); 
	 } 
	  
	 /**
	  *  
	  * @param tooltip 
	  * @return 
	  */ 
	 public SWTBotToolbarButton getToolbarButton(String tooltip) { 
	  return bot.toolbarButtonWithTooltip(tooltip); 
	 } 
	  
	 /**
	  *  
	  * @param label 
	  * @return 
	  */ 
	 public SWTBotText getText(String label) { 
	  return bot.textWithLabel(label); 
	 } 
	  
	 /**
	  *  
	  * @param label 
	  * @return 
	  */ 
	 public SWTBotCombo getComboBox(String label) { 
	  return bot.comboBoxWithLabel(label); 
	 } 
	  
	 /**
	  *  
	  * @param expanded 
	  */ 

	 /**
	  *  
	  * @param name 
	  * @return 
	  */ 
	 public static Section getSection(final String name) { 
	  return UIThreadRunnable.syncExec(new Result<Section>() { 
	 
	   public Section run() { 
	    List<? extends Section> sectionList = new SWTBot().widgets(WidgetMatcherFactory.widgetOfType(Section.class)); 
	    for (Section s : sectionList) { 
	     if (name.equals(s.getText())) { 
	      return s; 
	     } 
	    } 
	    return null; 
	   } 
	    
	  }); 
	   
	 } 
	 
	 
	 public Control getControl() { 
	  return widget; 
	 } 

	
	}
