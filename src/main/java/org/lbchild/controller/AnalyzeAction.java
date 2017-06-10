package org.lbchild.controller;
import org.eclipse.jface.action.Action;
import org.lbchild.window.AnalysisWindow;;

public class AnalyzeAction extends Action {

    public void run(){
        AnalysisWindow analysisWindow = new AnalysisWindow();
        analysisWindow.open();
    }
    
    public AnalyzeAction(String str){
        super();
        setText(str);
    }
}
