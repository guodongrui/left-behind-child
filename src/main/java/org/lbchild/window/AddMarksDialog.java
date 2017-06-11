package org.lbchild.window;

import java.util.ArrayList;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Text;
import org.lbchild.controller.AddMarksReadMoreWindowListener;
import org.lbchild.model.NewsList;
import org.lbchild.res.management.SWTResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;

public class AddMarksDialog {

    Shell shell;

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
    private Button btnNext;
    
    private static AddMarksDialog addMarksDialog;

    ArrayList<ArrayList<Button>> btnMarks;

    private int lineId;
    private static Logger logger = LoggerFactory.getLogger(AddMarksDialog.class);    
    

    /**
     * Create the dialog.
     * 
     * @param parentShell
     */
    public AddMarksDialog(Shell parentShell, NewsList newsList, int lineId, String path) {
        shell = new Shell(parentShell, SWT.CLOSE | SWT.BORDER | SWT.TITLE);
        FillLayout layout = new FillLayout();
        shell.setLayout(layout);
        shell.addShellListener(new ShellAdapter() {
            @Override
            public void shellClosed(ShellEvent e) {
                // hide shell for later use
                e.doit = false;
                shell.setVisible(false);
            }
        });
        this.lineId = lineId;
       
        
        ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.H_SCROLL|SWT.V_SCROLL|SWT.BORDER);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true); 
        
        Composite composite = new Composite(scrolledComposite, SWT.BORDER);
        composite.setLayout(new GridLayout(2, false));
        Composite composite_1 = new Composite(composite, SWT.NONE);
        composite_1.setLayout(new GridLayout(1, false));
        composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));


        Composite composite_4 = new Composite(composite_1, SWT.NONE);
        composite_4.setLayout(new GridLayout(1, false));
        GridData gd_composite_4 = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd_composite_4.widthHint = 296;
        composite_4.setLayoutData(gd_composite_4);

        text_Type = new Text(composite_4, SWT.READ_ONLY);
        text_Type.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Type.setText("报纸类别");

        Button btnRadioButtonTypeCenter = new Button(composite_4, SWT.RADIO);
        btnRadioButtonTypeCenter.setText("中央一级");

        Button btnRadioButtonTypeProvince = new Button(composite_4, SWT.RADIO);
        btnRadioButtonTypeProvince.setText("省一级");

        Button btnRadioButtonTypeMarket = new Button(composite_4, SWT.RADIO);
        btnRadioButtonTypeMarket.setText("经营模式市场化");

        final ArrayList<Button> btnType = new ArrayList<>();
        btnType.add(btnRadioButtonTypeCenter);
        btnType.add(btnRadioButtonTypeProvince);
        btnType.add(btnRadioButtonTypeMarket);

        Composite composite_5 = new Composite(composite_1, SWT.NONE);
        composite_5.setLayout(new GridLayout(1, false));
        composite_5.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        text_Theme = new Text(composite_5, SWT.READ_ONLY);
        text_Theme.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Theme.setText("报道主题");

        Button btnRadioButtonThemeHelp = new Button(composite_5, SWT.RADIO);
        btnRadioButtonThemeHelp.setText("社会各界帮助关爱");

        Button btnRadioButtonThemeAdvice = new Button(composite_5, SWT.RADIO);
        btnRadioButtonThemeAdvice.setText("社会各界对留守儿童现象提出的建议和看法");

        Button btnRadioButtonThemePraise = new Button(composite_5, SWT.RADIO);
        btnRadioButtonThemePraise.setText("表彰帮助关爱留守儿童的单位或个人");

        Button btnRadioButtonThemeViolent = new Button(composite_5, SWT.RADIO);
        btnRadioButtonThemeViolent.setText("留守儿童遭受暴力");

        Button btnRadioButtonThemeAbuse = new Button(composite_5, SWT.RADIO);
        btnRadioButtonThemeAbuse.setText("留守儿童被性侵、猥亵、强奸或是怀孕、生子等");

        Button btnRadioButtonThemeCriminal = new Button(composite_5, SWT.RADIO);
        btnRadioButtonThemeCriminal.setText("留守儿童犯罪等");

        Button btnRadioButtonThemeDeath = new Button(composite_5, SWT.RADIO);
        btnRadioButtonThemeDeath.setText("留守儿童意外死亡");

        Button btnRadioButtonThemeEffort = new Button(composite_5, SWT.RADIO);
        btnRadioButtonThemeEffort.setText("留守儿童努力上进");

        Button btnRadioButtonThemeRough = new Button(composite_5, SWT.RADIO);
        btnRadioButtonThemeRough.setText("打工父母在城市艰难生活");

        Button btnRadioButtonThemeElse = new Button(composite_5, SWT.RADIO);
        btnRadioButtonThemeElse.setText("其他");

        final ArrayList<Button> btnTheme = new ArrayList<>();
        btnTheme.add(btnRadioButtonThemeHelp);
        btnTheme.add(btnRadioButtonThemeAdvice);
        btnTheme.add(btnRadioButtonThemePraise);
        btnTheme.add(btnRadioButtonThemeViolent);
        btnTheme.add(btnRadioButtonThemeAbuse);
        btnTheme.add(btnRadioButtonThemeCriminal);
        btnTheme.add(btnRadioButtonThemeDeath);
        btnTheme.add(btnRadioButtonThemeEffort);
        btnTheme.add(btnRadioButtonThemeRough);
        btnTheme.add(btnRadioButtonThemeElse);

        Composite composite_6 = new Composite(composite_1, SWT.NONE);
        composite_6.setLayout(new GridLayout(1, false));
        composite_6.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        text_Source = new Text(composite_6, SWT.READ_ONLY);
        text_Source.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Source.setText("新闻报道消息来源");

        Button btnRadioButtonSourceReporter = new Button(composite_6, SWT.RADIO);
        btnRadioButtonSourceReporter.setText("记者");

        Button btnRadioButtonSourceGovern = new Button(composite_6, SWT.RADIO);
        btnRadioButtonSourceGovern.setText("政府");

        Button btnRadioButtonSourceEnte = new Button(composite_6, SWT.RADIO);
        btnRadioButtonSourceEnte.setText("企业");

        Button btnRadioButtonSourceTeam = new Button(composite_6, SWT.RADIO);
        btnRadioButtonSourceTeam.setText("事业单位");

        Button btnRadioButtonSourceExpert = new Button(composite_6, SWT.RADIO);
        btnRadioButtonSourceExpert.setText("专家学者");

        Button btnRadioButtonSourceInstitution = new Button(composite_6, SWT.RADIO);
        btnRadioButtonSourceInstitution.setText("公益团体");

        Button btnRadioButtonSourceLeader = new Button(composite_6, SWT.RADIO);
        btnRadioButtonSourceLeader.setText("政府领导、政协或人大代表");

        Button btnRadioButtonSourceElse = new Button(composite_6, SWT.RADIO);
        btnRadioButtonSourceElse.setText("其他");

        final ArrayList<Button> btnSource = new ArrayList<>();
        btnSource.add(btnRadioButtonSourceReporter);
        btnSource.add(btnRadioButtonSourceGovern);
        btnSource.add(btnRadioButtonSourceEnte);
        btnSource.add(btnRadioButtonSourceTeam);
        btnSource.add(btnRadioButtonSourceExpert);
        btnSource.add(btnRadioButtonSourceInstitution);
        btnSource.add(btnRadioButtonSourceLeader);
        btnSource.add(btnRadioButtonSourceElse);

        Composite composite_7 = new Composite(composite_1, SWT.NONE);
        composite_7.setLayout(new GridLayout(1, false));
        composite_7.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        text_Sex = new Text(composite_7, SWT.READ_ONLY);
        text_Sex.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Sex.setText("性別");

        Button btnRadioButtonSexMan = new Button(composite_7, SWT.RADIO);
        btnRadioButtonSexMan.setText("男");

        Button btnRadioButtonSexWoman = new Button(composite_7, SWT.RADIO);
        btnRadioButtonSexWoman.setText("女");

        ArrayList<Button> btnSex = new ArrayList<>();
        btnSex.add(btnRadioButtonSexMan);
        btnSex.add(btnRadioButtonSexWoman);

        Composite composite_8 = new Composite(composite_1, SWT.NONE);
        composite_8.setLayout(new GridLayout(1, false));
        composite_8.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        text_Reason = new Text(composite_8, SWT.READ_ONLY);
        text_Reason.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Reason.setText("新闻报道中农民工子女不能留在城市读书的原因");

        Button btnRadioButtonReasonRegister = new Button(composite_8, SWT.RADIO);
        btnRadioButtonReasonRegister.setText("无本地户籍难入公立学校");

        Button btnRadioButtonReasonFee = new Button(composite_8, SWT.RADIO);
        btnRadioButtonReasonFee.setText("私立学校学费高");

        Button btnRadioButtonReasonQuality = new Button(composite_8, SWT.RADIO);
        btnRadioButtonReasonQuality.setText("私立学校教学质量没保障");

        Button btnRadioButtonReasonCancel = new Button(composite_8, SWT.RADIO);
        btnRadioButtonReasonCancel.setText("越来越多的小型私立学校被国家取消办学资格");

        Button btnRadioButtonReasonElse = new Button(composite_8, SWT.RADIO);
        btnRadioButtonReasonElse.setText("其他");

        final ArrayList<Button> btnReason = new ArrayList<>();
        btnReason.add(btnRadioButtonReasonRegister);
        btnReason.add(btnRadioButtonReasonFee);
        btnReason.add(btnRadioButtonReasonQuality);
        btnReason.add(btnRadioButtonReasonCancel);
        btnReason.add(btnRadioButtonReasonElse);

        Composite composite_2 = new Composite(composite, SWT.NONE);
        GridData gd_composite_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
        gd_composite_2.widthHint = 162;
        composite_2.setLayoutData(gd_composite_2);
        composite_2.setBounds(298, 5, 212, 688);
        composite_2.setLayout(new GridLayout(1, false));
        GridData gd_composite_1 = new GridData(SWT.FILL, SWT.FILL, true, true);
        gd_composite_1.widthHint = 309;
        composite_1.setLayoutData(gd_composite_1);
        // scrolledComposite.setContent(composite);
        // scrolledComposite.setMinSize(composite.computeSize(SWT.DEFAULT,
        // SWT.DEFAULT));

        Composite composite_3 = new Composite(composite_2, SWT.NONE);
        composite_3.setLayout(new GridLayout(1, false));
        composite_3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));


        text_NType = new Text(composite_3, SWT.READ_ONLY);
        text_NType.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_NType.setText("新闻类型");

        Button btnRadioButtonNTypePure = new Button(composite_3, SWT.RADIO);
        btnRadioButtonNTypePure.setText("纯净新闻");

        Button btnRadioButtonNTypeFeature = new Button(composite_3, SWT.RADIO);
        btnRadioButtonNTypeFeature.setText("特稿与特写");

        Button btnRadioButtonNTypeComment = new Button(composite_3, SWT.RADIO);
        btnRadioButtonNTypeComment.setText("评论");

        Button btnRadioButtonNTypeElse = new Button(composite_3, SWT.RADIO);
        btnRadioButtonNTypeElse.setText("其他");

        final ArrayList<Button> btnNType = new ArrayList<>();
        btnNType.add(btnRadioButtonNTypePure);
        btnNType.add(btnRadioButtonNTypeFeature);
        btnNType.add(btnRadioButtonNTypeComment);
        btnNType.add(btnRadioButtonNTypeElse);
        new Label(composite_2, SWT.NONE);

        Composite composite_9 = new Composite(composite_2, SWT.NONE);
        composite_9.setLayout(new GridLayout(1, false));
        composite_9.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        text_Image = new Text(composite_9, SWT.READ_ONLY);
        text_Image.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Image.setText("媒体呈现形象");

        Button btnRadioButtonImagePositive = new Button(composite_9, SWT.RADIO);
        btnRadioButtonImagePositive.setText("积极健康");

        Button btnRadioButtonImagePoor = new Button(composite_9, SWT.RADIO);
        btnRadioButtonImagePoor.setText("可伶悲惨");

        Button btnRadioButtonImageHappiness = new Button(composite_9, SWT.RADIO);
        btnRadioButtonImageHappiness.setText("沫恩幸福");

        Button btnRadioButtonImageProblem = new Button(composite_9, SWT.RADIO);
        btnRadioButtonImageProblem.setText("问题儿童");

        Button btnRadioButtonImageElse = new Button(composite_9, SWT.RADIO);
        btnRadioButtonImageElse.setText("其他");

        final ArrayList<Button> btnImage = new ArrayList<>();
        btnImage.add(btnRadioButtonImagePositive);
        btnImage.add(btnRadioButtonImagePoor);
        btnImage.add(btnRadioButtonImageHappiness);
        btnImage.add(btnRadioButtonImageProblem);
        btnImage.add(btnRadioButtonImageElse);
        new Label(composite_2, SWT.NONE);

        Composite composite_10 = new Composite(composite_2, SWT.NONE);
        composite_10.setLayout(new GridLayout(1, false));
        composite_10.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        text_Subject = new Text(composite_10, SWT.READ_ONLY);
        text_Subject.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Subject.setText("帮助类新闻的主体");

        Button btnRadioButtonSubjectGovern = new Button(composite_10, SWT.RADIO);
        btnRadioButtonSubjectGovern.setText("政府部门");

        Button btnRadioButtonSubjectFirm = new Button(composite_10, SWT.RADIO);
        btnRadioButtonSubjectFirm.setText("企业");

        Button btnRadioButtonSubjectUnits = new Button(composite_10, SWT.RADIO);
        btnRadioButtonSubjectUnits.setText("事业单位");

        Button btnRadioButtonSubjectInterest = new Button(composite_10, SWT.RADIO);
        btnRadioButtonSubjectInterest.setText("公益团体");

        Button btnRadioButtonSubjectIndividual = new Button(composite_10, SWT.RADIO);
        btnRadioButtonSubjectIndividual.setText("个人");

        final ArrayList<Button> btnSubject = new ArrayList<>();
        btnSubject.add(btnRadioButtonSubjectGovern);
        btnSubject.add(btnRadioButtonSubjectFirm);
        btnSubject.add(btnRadioButtonSubjectUnits);
        btnSubject.add(btnRadioButtonSubjectInterest);
        btnSubject.add(btnRadioButtonSubjectIndividual);
        new Label(composite_2, SWT.NONE);

        Composite composite_11 = new Composite(composite_2, SWT.NONE);
        composite_11.setLayout(new GridLayout(1, false));
        composite_11.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        text_Specific = new Text(composite_11, SWT.READ_ONLY);
        text_Specific.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Specific.setText("帮助新闻的具体种类");

        Button btnRadioButtonSpecificDonate = new Button(composite_11, SWT.RADIO);
        btnRadioButtonSpecificDonate.setText("单纯一次捐款捐物");

        Button btnRadioButtonSpecificTravel = new Button(composite_11, SWT.RADIO);
        btnRadioButtonSpecificTravel.setText("旅游活动安排的项目之一");

        Button btnRadioButtonSpecificFree = new Button(composite_11, SWT.RADIO);
        btnRadioButtonSpecificFree.setText("免费开放");

        Button btnRadioButtonSpecificLong = new Button(composite_11, SWT.RADIO);
        btnRadioButtonSpecificLong.setText("设立长期资助项目");

        Button btnRadioButtonSpecificElse = new Button(composite_11, SWT.RADIO);
        btnRadioButtonSpecificElse.setText("其他");

        final ArrayList<Button> btnSpecific = new ArrayList<>();
        btnSpecific.add(btnRadioButtonSpecificDonate);
        btnSpecific.add(btnRadioButtonSpecificTravel);
        btnSpecific.add(btnRadioButtonSpecificFree);
        btnSpecific.add(btnRadioButtonSpecificLong);
        btnSpecific.add(btnRadioButtonSpecificElse);
        new Label(composite_2, SWT.NONE);

        Composite composite_12 = new Composite(composite_2, SWT.NONE);
        composite_12.setLayout(new GridLayout(1, false));
        composite_12.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        text_Praise = new Text(composite_12, SWT.READ_ONLY);
        text_Praise.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Praise.setText("表彰奖励的新闻主体");

        Button btnRadioButtonPraiseGovern = new Button(composite_12, SWT.RADIO);
        btnRadioButtonPraiseGovern.setText("政府部门");

        Button btnRadioButtonPraiseFirm = new Button(composite_12, SWT.RADIO);
        btnRadioButtonPraiseFirm.setText("企业");

        Button btnRadioButtonPraiseUnits = new Button(composite_12, SWT.RADIO);
        btnRadioButtonPraiseUnits.setText("事业单位");

        Button btnRadioButtonPraiseInterest = new Button(composite_12, SWT.RADIO);
        btnRadioButtonPraiseInterest.setText("公益团体");

        Button btnRadioButtonPraiseIndividual = new Button(composite_12, SWT.RADIO);
        btnRadioButtonPraiseIndividual.setText("个人");

        final ArrayList<Button> btnPraise = new ArrayList<>();
        btnPraise.add(btnRadioButtonPraiseGovern);
        btnPraise.add(btnRadioButtonPraiseFirm);
        btnPraise.add(btnRadioButtonPraiseUnits);
        btnPraise.add(btnRadioButtonPraiseInterest);
        btnPraise.add(btnRadioButtonPraiseIndividual);

        btnMarks = new ArrayList<>();
        btnMarks.add(btnType);
        btnMarks.add(btnNType);
        btnMarks.add(btnTheme);
        btnMarks.add(btnSource);
        btnMarks.add(btnImage);
        btnMarks.add(btnSpecific);
        btnMarks.add(btnSubject);
        btnMarks.add(btnPraise);
        btnMarks.add(btnReason);
        btnMarks.add(btnSex);
//        for (ArrayList<Button> markGroupType : btnMarks) {
//            markGroupType.get(0).setSelection(true);
//        }

        btnNext = new Button(composite_2, SWT.NONE);
        btnNext.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
        btnNext.setText("Next");
        AddMarksReadMoreWindowListener addmarksListener = new AddMarksReadMoreWindowListener(newsList, btnMarks, path);
        btnNext.addSelectionListener(addmarksListener);
        TrainWindow win = TrainWindow.getTrainWindow();
        if (win != null) {
            btnNext.addSelectionListener(TrainWindow.getTrainWindow().getFinishTrainingListener());
            logger.info("add finishTrainingListener in training mode");
        }
       
        scrolledComposite.setContent(composite);
        scrolledComposite.setMinWidth(500);
        scrolledComposite.setMinHeight(820); 
        shell.setSize(530, 593);

        //shell.pack();
    }

    public void open() {
        if (shell.isVisible()) {
            shell.setFocus();
        } else {
            shell.open();
        }
    }

    public static AddMarksDialog getAddMarksDialog() {
        return addMarksDialog;
    }
    
    public int getLineId() {
        return lineId;
    }

    public void setLineId(int lineId) {
        this.lineId = lineId;
    }
    
    public Button getNextButton() {
        return btnNext;
    }
}
