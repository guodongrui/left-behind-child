package org.lbchild.controller;
import org.eclipse.jface.action.Action;
import org.lbchild.model.NewsList;
import org.lbchild.window.AnalysisWindow;;

public class AnalyzeAction extends Action {

    //private NewsList newslist;
    public AnalyzeAction(NewsList newslist) {
        // TODO Auto-generated constructor stub
        super();
        //this.newslist = newslist;
    }

    public void run(){
        AnalysisWindow analysisWindow = new AnalysisWindow();
        analysisWindow.open();
    }
    
    public AnalyzeAction(String str){
        super();
        setText(str);
    }
}
