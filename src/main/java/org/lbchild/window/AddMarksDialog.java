package org.lbchild.window;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.lbchild.model.NewsList;
import org.lbchild.res.management.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;

public class AddMarksDialog extends Dialog {
   
    private Text text_Type;
    private Text text_Theme;
    private Text text_Source;
    private Text text_Reason;
    private Text text_NType;
    private Text text_Specific;
    private Text text_Image;
    private Text text_Subject;
    private Text text_Praise;
    private Text text_Sex;
    /**
     * Create the dialog.
     * @param parentShell
     */
    public AddMarksDialog(Shell parentShell) {
        super(parentShell);
    }
    
   
    /**
     * Create contents of the dialog.
     * @param parent
     */
    @Override
    protected Control createDialogArea(Composite parent) {
        Composite container = (Composite) super.createDialogArea(parent);
        container.setLayout(new GridLayout(7, false));
        
        Composite composite1 = new Composite(container, SWT.NONE);
        composite1.setLayout(new GridLayout(1, false));
        GridData gd_composite1 = new GridData(SWT.RIGHT, SWT.TOP, false, false, 5, 1);
        gd_composite1.widthHint = 284;
        gd_composite1.heightHint = 560;
        composite1.setLayoutData(gd_composite1);
        
        
        text_Type = new Text(composite1, SWT.BORDER);
        text_Type.setText("报纸类别");
        
         Button btnRadioButtonTypeCenter = new Button(composite1, SWT.RADIO);
         btnRadioButtonTypeCenter.setText("中央一级");
         
        Button btnRadioButtonTypeProvince = new Button(composite1, SWT.RADIO);
        btnRadioButtonTypeProvince.setText("省一级");
        
        Button btnRadioButtonTypeMarket = new Button(composite1, SWT.RADIO);
        btnRadioButtonTypeMarket.setText("经营模式市场化");
        
        text_Theme = new Text(composite1, SWT.BORDER);
        text_Theme.setText("报道主题");
        
        Button btnRadioButtonThemeHelp = new Button(composite1, SWT.RADIO);
        btnRadioButtonThemeHelp.setText("社会各界帮助关爱");
        
        Button btnRadioButtonThemeAdvice = new Button(composite1, SWT.RADIO);
        btnRadioButtonThemeAdvice.setText("社会各界对留守儿童现象提出的建议和看法");
        
        Button btnRadioButtonThemePraise = new Button(composite1, SWT.RADIO);
        btnRadioButtonThemePraise.setText("表彰帮助关爱留守儿童的单位或个人");
        
        Button btnRadioButtonThemeViolent = new Button(composite1, SWT.RADIO);
        btnRadioButtonThemeViolent.setText("留守儿童遭受暴力");
        
        Button btnRadioButtonThemeAbuse = new Button(composite1, SWT.RADIO);
        btnRadioButtonThemeAbuse.setText("留守儿童被性侵、猥亵、强奸或是怀孕、生子等");
        
        Button btnRadioButtonThemeCriminal = new Button(composite1, SWT.RADIO);
        btnRadioButtonThemeCriminal.setText("留守儿童犯罪等");
        
        Button btnRadioButtonThemeDeath = new Button(composite1, SWT.RADIO);
        btnRadioButtonThemeDeath.setText("留守儿童意外死亡");
        
        Button btnRadioButtonThemeEffort = new Button(composite1, SWT.RADIO);
        btnRadioButtonThemeEffort.setText("留守儿童努力上进");
        
        Button btnRadioButtonThemeRough = new Button(composite1, SWT.RADIO);
        btnRadioButtonThemeRough.setText("打工父母在城市艰难生活");
        
        Button btnRadioButtonThemeElse = new Button(composite1, SWT.RADIO);
        btnRadioButtonThemeElse.setText("其他");
        
             text_Source = new Text(composite1, SWT.BORDER);
             text_Source.setText("新闻报道消息来源");
         
             Button btnRadioButtonSourceReporter = new Button(composite1, SWT.RADIO);
             btnRadioButtonSourceReporter.setText("记者");
           
             Button btnRadioButtonSourceGovern = new Button(composite1, SWT.RADIO);
             btnRadioButtonSourceGovern.setText("政府");
        
             Button btnRadioButtonSourceEnte = new Button(composite1, SWT.RADIO);
             btnRadioButtonSourceEnte.setText("企业");
         
             Button btnRadioButtonSourceTeam = new Button(composite1, SWT.RADIO);
             btnRadioButtonSourceTeam.setText("事业单位");
          
             Button btnRadioButtonSourceExpert = new Button(composite1, SWT.RADIO);
             btnRadioButtonSourceExpert.setText("专家学者");
           
             Button btnRadioButtonSourceInstitution = new Button(composite1, SWT.RADIO);
             btnRadioButtonSourceInstitution.setText("公益团体");
            
             Button btnRadioButtonSourceLeader = new Button(composite1, SWT.RADIO);
             btnRadioButtonSourceLeader.setText("政府领导、政协或人大代表");
             
             Button btnRadioButtonSourceElse = new Button(composite1, SWT.RADIO);
             btnRadioButtonSourceElse.setText("其他");
             
            
             
        
        Composite composite2 = new Composite(container, SWT.NONE);
        GridData gd_composite2 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
        gd_composite2.heightHint = 562;
        composite2.setLayoutData(gd_composite2);
        composite2.setLayout(new GridLayout(1, false));
        
        text_Reason = new Text(composite2, SWT.BORDER);
        text_Reason.setText("新闻报道中农民工子女不能留在城市读书的原因");
        
        Button btnRadioButtonReasonRegister = new Button(composite2, SWT.RADIO);
        btnRadioButtonReasonRegister.setText("无本地户籍难入公立学校");
        
         Button btnRadioButtonReasonFee = new Button(composite2, SWT.RADIO);
         btnRadioButtonReasonFee.setText("私立学校学费高");
     
         Button btnRadioButtonReasonQuality = new Button(composite2, SWT.RADIO);
         btnRadioButtonReasonQuality.setText("私立学校教学质量没保障");
     
         Button btnRadioButtonReasonCancel = new Button(composite2, SWT.RADIO);
         btnRadioButtonReasonCancel.setText("越来越多的小型私立学校被国家取消办学资格");
      
         Button btnRadioButtonReasonElse = new Button(composite2, SWT.RADIO);
         btnRadioButtonReasonElse.setText("其他");
      
        
         
         text_Image = new Text(composite2, SWT.BORDER);
         text_Image.setText("媒体呈现形象");
         text_Image.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
         
         Button btnRadioButtonImagePositive = new Button(composite2, SWT.RADIO);
         btnRadioButtonImagePositive.setText("积极健康");
         
         Button btnRadioButtonImagePoor = new Button(composite2, SWT.RADIO);
         btnRadioButtonImagePoor.setText("可伶悲惨");
         
         Button btnRadioButtonImageHappiness = new Button(composite2, SWT.RADIO);
         btnRadioButtonImageHappiness.setText("沫恩幸福");
         
         Button btnRadioButtonImageProblem = new Button(composite2, SWT.RADIO);
         btnRadioButtonImageProblem.setText("问题儿童");
         
         Button btnRadioButtonImageElse = new Button(composite2, SWT.RADIO);
         btnRadioButtonImageElse.setText("其他");
         
         text_Subject = new Text(composite2, SWT.BORDER);
         text_Subject.setText("帮助类新闻的主体");
         text_Subject.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
         
         Button btnRadioButtonSubjectGovern = new Button(composite2, SWT.RADIO);
         btnRadioButtonSubjectGovern.setText("政府部门");
         
         Button btnRadioButtonSubjectFirm = new Button(composite2, SWT.RADIO);
         btnRadioButtonSubjectFirm.setText("企业");
         
         Button btnRadioButtonSubjectUnits = new Button(composite2, SWT.RADIO);
         btnRadioButtonSubjectUnits.setText("事业单位");
         
         Button btnRadioButtonSubjectInterest = new Button(composite2, SWT.RADIO);
         btnRadioButtonSubjectInterest.setText("公益团体");
         
         Button btnRadioButtonSubjectIndividual = new Button(composite2, SWT.RADIO);
         btnRadioButtonSubjectIndividual.setText("个人");
         
         text_Specific = new Text(composite2, SWT.BORDER);
         text_Specific.setText("帮助新闻的具体种类");
         text_Specific.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
         
         Button btnRadioButtonSpecificDonate = new Button(composite2, SWT.RADIO);
         btnRadioButtonSpecificDonate.setText("单纯一次捐款捐物");
         
         Button btnRadioButtonSpecificTravel = new Button(composite2, SWT.RADIO);
         btnRadioButtonSpecificTravel.setText("旅游活动安排的项目之一");
         
         Button btnRadioButtonSpecificFree = new Button(composite2, SWT.RADIO);
         btnRadioButtonSpecificFree.setText("免费开放");
         
         Button btnRadioButtonSpecificLong = new Button(composite2, SWT.RADIO);
         btnRadioButtonSpecificLong.setText("设立长期资助项目");
         
         Button btnRadioButtonSpecificElse = new Button(composite2, SWT.RADIO);
         btnRadioButtonSpecificElse.setText("其他");
         
         
         
         Composite composite3 = new Composite(container, SWT.NONE);
         GridData gd_composite3 = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
         gd_composite3.heightHint = 563;
         composite3.setLayoutData(gd_composite3);
         composite3.setLayout(new GridLayout(1, false));
         
         text_NType = new Text(composite3, SWT.BORDER);
         text_NType.setText("新闻类型");
         text_NType.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
         
          Button btnRadioButtonNTypePure = new Button(composite3, SWT.RADIO);
          btnRadioButtonNTypePure.setText("纯净新闻");
          
          Button btnRadioButtonNTypeFeature = new Button(composite3, SWT.RADIO);
          btnRadioButtonNTypeFeature.setText("特稿与特写");
          
          Button btnRadioButtonNTypeComment = new Button(composite3, SWT.RADIO);
          btnRadioButtonNTypeComment.setText("评论");
          
          Button btnRadioButtonNTypeElse = new Button(composite3, SWT.RADIO);
          btnRadioButtonNTypeElse.setText("其他");
         
         text_Praise = new Text(composite3, SWT.BORDER);
         text_Praise.setText("表彰奖励的新闻主体");
         text_Praise.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
         
         Button btnRadioButtonPraiseGovern = new Button(composite3, SWT.RADIO);
         btnRadioButtonPraiseGovern.setText("政府部门");
         
         Button btnRadioButtonPraiseFirm = new Button(composite3, SWT.RADIO);
         btnRadioButtonPraiseFirm.setText("企业");
         
         Button btnRadioButtonPraiseUnits = new Button(composite3, SWT.RADIO);
         btnRadioButtonPraiseUnits.setText("事业单位");
         
         Button btnRadioButtonPraiseInterest = new Button(composite3, SWT.RADIO);
         btnRadioButtonPraiseInterest.setText("公益团体");
         
         Button btnRadioButtonPraiseIndividual = new Button(composite3, SWT.RADIO);
         btnRadioButtonPraiseIndividual.setText("个人");
         
         text_Sex = new Text(composite3, SWT.BORDER);
         text_Sex.setText("性別");
         text_Sex.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
         
         Button btnRadioButtonSexMan = new Button(composite3, SWT.RADIO);
         btnRadioButtonSexMan.setText("男");
         
         Button btnRadioButtonSexWoman = new Button(composite3, SWT.RADIO);
         btnRadioButtonSexWoman.setText("女");
   
         return container;
    }

    /**
     * Create contents of the button bar.
     * @param parent
     */
    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
        createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);
    }

    /**
     * Return the initial size of the dialog.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(746, 718);
    }
}
