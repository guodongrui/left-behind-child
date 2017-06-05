package org.lbchild.window;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.lbchild.controller.AddMarksListener;

import org.lbchild.controller.ReadMoreListener;
import org.lbchild.model.NewsItem;
import org.lbchild.model.NewsList;
import org.lbchild.res.management.SWTResourceManager;
import org.lbchild.util.Base64Content;
import org.lbchild.xml.XMLReader;

public class TrainWindow extends ApplicationWindow {
	
    private Text text_Type;
    private Text text_Theme;
    private Text text_Source;
    private Text text_Reason;
    private Text text_NType;
    private Text text_Image;
    private Text text_Subject;
    private Text text_Specific;
    private Text text_Praise;
    private Text text_Sex;
    private NewsList newsList;
	/**
	 * Create the application window.
	 */
	public TrainWindow() {
		super(null);
		init();
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}
	public void init() {
    	newsList = new NewsList();
    	readFile("src/main/resources/guangming.xml");
    	readFile("src/main/resources/nanfangdaily.xml");
    	readFile("src/main/resources/sichuan.xml");
	}
    private void readFile(String path) {
    	try {
            File file = new File(path);
            XMLReader in = new XMLReader(file);
            ArrayList<Map<String, String>> list = in.readXml();

            int size=list.size();
            ArrayList<NewsItem> li = new ArrayList<>();
            for (int i = 0; i < (10<size? 10:size); ++i) {
                NewsItem newsItem = new NewsItem();
                newsItem.setDate(list.get(i).get("Date"));
                newsItem.setTitle(list.get(i).get("Title"));
                String encodedContent = list.get(i).get("EncodedContent"); 
                String content = null;
                
                if (encodedContent != null) {
                    content = Base64Content.decode(encodedContent).replaceAll("</?html>|</?body>|</?P>", "");
                    
                   
                } else {
                    content = list.get(i).get("TrueUrl");
                    if (content == null)
                        content = list.get(i).get("Url");
                }
                newsItem.setContent(content);
                newsItem.setDeleted(Boolean.parseBoolean(list.get(i).get("IsDeleted")));
                newsItem.setLocation(list.get(i).get("Location"));
                li.add(newsItem);
                
            }

            newsList.addNewList(li);


        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
    @Override
    protected Control createContents(Composite parent) {
        Composite container = new Composite(parent, SWT.NONE);

        ScrolledComposite scrolledComposite = new ScrolledComposite(container,
                SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        scrolledComposite.setBounds(479, 0, 506, 644);
        scrolledComposite.setExpandHorizontal(true);
        scrolledComposite.setExpandVertical(true);

        final List newsSummaryList = new List(container, SWT.BORDER | SWT.V_SCROLL);
        newsSummaryList.setBounds(0, 0, 478, 613);
        newsSummaryList.setItems(newsList.getNewsSummaryList());
        newsSummaryList.addSelectionListener(new ReadMoreListener(newsList));

        Group group_AddMarks = new Group(scrolledComposite, SWT.NONE);

        Composite composite_Type = new Composite(group_AddMarks, SWT.NONE);
        composite_Type.setBounds(10, 10, 129, 82);

        text_Type = new Text(composite_Type, SWT.CENTER);
        text_Type.setBounds(0, 0, 73, 20);
        text_Type.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Type.setEnabled(true);
        text_Type.setEditable(false);
        text_Type.setText("报纸类别");

        Button btnRadioButtonTypeCenter = new Button(composite_Type, SWT.RADIO);
        btnRadioButtonTypeCenter.setBounds(0, 25, 97, 17);
        btnRadioButtonTypeCenter.setEnabled(true);
        btnRadioButtonTypeCenter.setText("中央一级");

        Button btnRadioButtonTypeProvince = new Button(composite_Type, SWT.RADIO);
        btnRadioButtonTypeProvince.setBounds(0, 45, 100, 17);
        btnRadioButtonTypeProvince.setEnabled(true);
        btnRadioButtonTypeProvince.setText("省一级");

        Button btnRadioButtonTypeMarket = new Button(composite_Type, SWT.RADIO);
        btnRadioButtonTypeMarket.setBounds(0, 65, 100, 17);
        btnRadioButtonTypeMarket.setEnabled(true);
        btnRadioButtonTypeMarket.setText("经营模式市场化");

        final ArrayList<Button> btnType = new ArrayList<>();
        btnType.add(btnRadioButtonTypeCenter);
        btnType.add(btnRadioButtonTypeProvince);
        btnType.add(btnRadioButtonTypeMarket);

        Composite composite_Theme = new Composite(group_AddMarks, SWT.NONE);
        composite_Theme.setBounds(10, 103, 300, 223);

        text_Theme = new Text(composite_Theme, SWT.CENTER);
        text_Theme.setBounds(0, 0, 73, 20);
        text_Theme.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
        text_Theme.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Theme.setEnabled(true);
        text_Theme.setEditable(false);
        text_Theme.setText("报道主题");

        Button btnRadioButtonThemeHelp = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeHelp.setBounds(0, 26, 120, 17);
        btnRadioButtonThemeHelp.setText("社会各界帮助关爱");

        Button btnRadioButtonThemeAdvice = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeAdvice.setBounds(0, 46, 250, 17);
        btnRadioButtonThemeAdvice.setText("社会各界对留守儿童现象提出的建议和看法");

        Button btnRadioButtonThemePraise = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemePraise.setBounds(0, 66, 250, 17);
        btnRadioButtonThemePraise.setText("表彰帮助关爱留守儿童的单位或个人");

        Button btnRadioButtonThemeViolent = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeViolent.setBounds(0, 86, 250, 17);
        btnRadioButtonThemeViolent.setText("留守儿童遭受暴力");

        Button btnRadioButtonThemeAbuse = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeAbuse.setBounds(0, 106, 300, 17);
        btnRadioButtonThemeAbuse.setText("留守儿童被性侵、猥亵、强奸或是怀孕、生子等");

        Button btnRadioButtonThemeCriminal = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeCriminal.setBounds(0, 126, 150, 17);
        btnRadioButtonThemeCriminal.setText("留守儿童犯罪等");

        Button btnRadioButtonThemeDeath = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeDeath.setBounds(0, 146, 150, 17);
        btnRadioButtonThemeDeath.setText("留守儿童意外死亡");

        Button btnRadioButtonThemeEffort = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeEffort.setBounds(0, 166, 150, 17);
        btnRadioButtonThemeEffort.setText("留守儿童努力上进");

        Button btnRadioButtonThemeRough = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeRough.setBounds(0, 186, 200, 17);
        btnRadioButtonThemeRough.setText("打工父母在城市艰难生活");

        Button btnRadioButtonThemeElse = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeElse.setBounds(0, 206, 100, 17);
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

        Composite composite_Source = new Composite(group_AddMarks, SWT.NONE);
        composite_Source.setBounds(10, 333, 200, 123);

        text_Source = new Text(composite_Source, SWT.CENTER);
        text_Source.setBounds(0, 0, 80, 20);
        text_Source.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Source.setEnabled(true);
        text_Source.setEditable(false);
        text_Source.setText("新闻报道来源");

        Button btnRadioButtonSourceReporter = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceReporter.setBounds(0, 26, 54, 17);
        btnRadioButtonSourceReporter.setText("记者");

        Button btnRadioButtonSourceGovern = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceGovern.setBounds(71, 26, 73, 17);
        btnRadioButtonSourceGovern.setText("政府");

        Button btnRadioButtonSourceEnte = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceEnte.setBounds(0, 46, 45, 17);
        btnRadioButtonSourceEnte.setText("企业");

        Button btnRadioButtonSourceInstitution = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceInstitution.setBounds(71, 46, 73, 17);
        btnRadioButtonSourceInstitution.setText("事业单位");

        Button btnRadioButtonSourceTeam = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceTeam.setBounds(0, 66, 69, 17);
        btnRadioButtonSourceTeam.setText("公益团体");

        Button btnRadioButtonSourceExpert = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceExpert.setBounds(71, 66, 100, 17);
        btnRadioButtonSourceExpert.setText("专家学者");

        Button btnRadioButtonSourceLeader = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceLeader.setBounds(0, 86, 200, 17);
        btnRadioButtonSourceLeader.setText("政府领导、政协或人大代表");

        Button btnRadioButtonSourceElse = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceElse.setBounds(0, 106, 150, 17);
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

        Composite composite_Reason = new Composite(group_AddMarks, SWT.NONE);
        composite_Reason.setBounds(10, 522, 270, 123);

        text_Reason = new Text(composite_Reason, SWT.NONE);
        text_Reason.setBounds(0, 0, 260, 20);
        text_Reason.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Reason.setEnabled(true);
        text_Reason.setEditable(false);
        text_Reason.setText("新闻报道中农民工子女不能留在城市读书的原因");

        Button btnRadioButtonReasonRegister = new Button(composite_Reason, SWT.RADIO);
        btnRadioButtonReasonRegister.setBounds(0, 26, 150, 17);
        btnRadioButtonReasonRegister.setText("无本地户籍难入公立学校");

        Button btnRadioButtonReasonFee = new Button(composite_Reason, SWT.RADIO);
        btnRadioButtonReasonFee.setBounds(0, 46, 129, 17);
        btnRadioButtonReasonFee.setText("私立学校学费高");

        Button btnRadioButtonReasonQuality = new Button(composite_Reason, SWT.RADIO);
        btnRadioButtonReasonQuality.setBounds(0, 66, 173, 17);
        btnRadioButtonReasonQuality.setText("私立学校教学质量没保障");

        Button btnRadioButtonReasonCancel = new Button(composite_Reason, SWT.RADIO);
        btnRadioButtonReasonCancel.setBounds(0, 86, 270, 17);
        btnRadioButtonReasonCancel.setText("越来越多的小型私立学校被国家取消办学资格");

        Button btnRadioButtonReasonElse = new Button(composite_Reason, SWT.RADIO);
        btnRadioButtonReasonElse.setBounds(0, 106, 80, 17);
        btnRadioButtonReasonElse.setText("其他");

        final ArrayList<Button> btnReason = new ArrayList<>();
        btnReason.add(btnRadioButtonReasonRegister);
        btnReason.add(btnRadioButtonReasonFee);
        btnReason.add(btnRadioButtonReasonQuality);
        btnReason.add(btnRadioButtonReasonCancel);
        btnReason.add(btnRadioButtonReasonElse);

        Composite composite_NType = new Composite(group_AddMarks, SWT.NONE);
        composite_NType.setBounds(319, 10, 144, 102);

        text_NType = new Text(composite_NType, SWT.CENTER);
        text_NType.setBounds(0, 0, 73, 20);
        text_NType.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_NType.setEnabled(true);
        text_NType.setEditable(false);
        text_NType.setText("新闻类型");

        Button btnRadioButtonNTypePure = new Button(composite_NType, SWT.RADIO);
        btnRadioButtonNTypePure.setBounds(0, 25, 100, 17);
        btnRadioButtonNTypePure.setText("纯净新闻");

        Button btnRadioButtonNTypeFeature = new Button(composite_NType, SWT.RADIO);
        btnRadioButtonNTypeFeature.setBounds(0, 45, 100, 17);
        btnRadioButtonNTypeFeature.setText("特稿与特写");

        Button btnRadioButtonNTypeComment = new Button(composite_NType, SWT.RADIO);
        btnRadioButtonNTypeComment.setBounds(0, 65, 97, 17);
        btnRadioButtonNTypeComment.setText("评论");

        Button btnRadioButtonNTypeElse = new Button(composite_NType, SWT.RADIO);
        btnRadioButtonNTypeElse.setBounds(0, 85, 97, 17);
        btnRadioButtonNTypeElse.setText("其他");

        final ArrayList<Button> btnNType = new ArrayList<>();
        btnNType.add(btnRadioButtonNTypePure);
        btnNType.add(btnRadioButtonNTypeFeature);
        btnNType.add(btnRadioButtonNTypeComment);
        btnNType.add(btnRadioButtonNTypeElse);

        Composite composite_Image = new Composite(group_AddMarks, SWT.NONE);
        composite_Image.setBounds(319, 123, 100, 123);

        text_Image = new Text(composite_Image, SWT.CENTER);
        text_Image.setBounds(0, 0, 90, 20);
        text_Image.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Image.setEnabled(true);
        text_Image.setEditable(false);
        text_Image.setText("媒体呈现形象");

        Button btnRadioButtonImagePositive = new Button(composite_Image, SWT.RADIO);
        btnRadioButtonImagePositive.setBounds(0, 26, 97, 17);
        btnRadioButtonImagePositive.setText("积极健康");

        Button btnRadioButtonImagePoor = new Button(composite_Image, SWT.RADIO);
        btnRadioButtonImagePoor.setBounds(0, 46, 100, 17);
        btnRadioButtonImagePoor.setText("可伶悲惨");

        Button btnRadioButtonImageHappiness = new Button(composite_Image, SWT.RADIO);
        btnRadioButtonImageHappiness.setBounds(0, 66, 100, 17);
        btnRadioButtonImageHappiness.setText("沫恩幸福");

        Button btnRadioButtonImageProblem = new Button(composite_Image, SWT.RADIO);
        btnRadioButtonImageProblem.setBounds(0, 86, 97, 17);
        btnRadioButtonImageProblem.setText("问题儿童");

        Button btnRadioButtonImageElse = new Button(composite_Image, SWT.RADIO);
        btnRadioButtonImageElse.setBounds(0, 106, 97, 17);
        btnRadioButtonImageElse.setText("其他");

        final ArrayList<Button> btnImage = new ArrayList<>();
        btnImage.add(btnRadioButtonImagePositive);
        btnImage.add(btnRadioButtonImagePoor);
        btnImage.add(btnRadioButtonImageHappiness);
        btnImage.add(btnRadioButtonImageProblem);
        btnImage.add(btnRadioButtonImageElse);

        Composite composite_Subject = new Composite(group_AddMarks, SWT.NONE);
        composite_Subject.setBounds(319, 249, 110, 123);

        text_Subject = new Text(composite_Subject, SWT.CENTER);
        text_Subject.setBounds(0, 0, 110, 20);
        text_Subject.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Subject.setEnabled(true);
        text_Subject.setEditable(false);
        text_Subject.setText("帮助类新闻的主体");

        Button btnRadioButtonSubjectGovern = new Button(composite_Subject, SWT.RADIO);
        btnRadioButtonSubjectGovern.setBounds(0, 26, 100, 17);
        btnRadioButtonSubjectGovern.setText("政府部门");

        Button btnRadioButtonSubjectFirm = new Button(composite_Subject, SWT.RADIO);
        btnRadioButtonSubjectFirm.setBounds(0, 46, 100, 17);
        btnRadioButtonSubjectFirm.setText("企业");

        Button btnRadioButtonSubjectUnits = new Button(composite_Subject, SWT.RADIO);
        btnRadioButtonSubjectUnits.setBounds(0, 66, 97, 17);
        btnRadioButtonSubjectUnits.setText("事业单位");

        Button btnRadioButtonSubjectInterest = new Button(composite_Subject, SWT.RADIO);
        btnRadioButtonSubjectInterest.setBounds(0, 86, 97, 17);
        btnRadioButtonSubjectInterest.setText("公益团体");

        Button btnRadioButtonSubjectIndividual = new Button(composite_Subject, SWT.RADIO);
        btnRadioButtonSubjectIndividual.setBounds(0, 106, 97, 17);
        btnRadioButtonSubjectIndividual.setText("个人");

        final ArrayList<Button> btnSubject = new ArrayList<>();
        btnSubject.add(btnRadioButtonSubjectGovern);
        btnSubject.add(btnRadioButtonSubjectFirm);
        btnSubject.add(btnRadioButtonSubjectUnits);
        btnSubject.add(btnRadioButtonSubjectInterest);
        btnSubject.add(btnRadioButtonSubjectIndividual);

        Composite composite_Specific = new Composite(group_AddMarks, SWT.NONE);
        composite_Specific.setBounds(319, 373, 120, 123);

        text_Specific = new Text(composite_Specific, SWT.CENTER);
        text_Specific.setBounds(0, 0, 120, 20);
        text_Specific.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Specific.setEnabled(true);
        text_Specific.setEditable(false);
        text_Specific.setText("帮助新闻的具体种类");

        Button btnRadioButtonSpecificDonate = new Button(composite_Specific, SWT.RADIO);
        btnRadioButtonSpecificDonate.setBounds(0, 26, 100, 17);
        btnRadioButtonSpecificDonate.setText("单纯一次捐款捐物");

        Button btnRadioButtonSpecificTravel = new Button(composite_Specific, SWT.RADIO);
        btnRadioButtonSpecificTravel.setBounds(0, 46, 100, 17);
        btnRadioButtonSpecificTravel.setText("旅游活动安排的项目之一");

        Button btnRadioButtonSpecificFree = new Button(composite_Specific, SWT.RADIO);
        btnRadioButtonSpecificFree.setBounds(0, 66, 97, 17);
        btnRadioButtonSpecificFree.setText("免费开放");

        Button btnRadioButtonSpecificLong = new Button(composite_Specific, SWT.RADIO);
        btnRadioButtonSpecificLong.setBounds(0, 86, 97, 17);
        btnRadioButtonSpecificLong.setText("设立长期资助项目");

        Button btnRadioButtonSpecificElse = new Button(composite_Specific, SWT.RADIO);
        btnRadioButtonSpecificElse.setBounds(0, 106, 97, 17);
        btnRadioButtonSpecificElse.setText("其他");

        final ArrayList<Button> btnSpecific = new ArrayList<>();
        btnSpecific.add(btnRadioButtonSpecificDonate);
        btnSpecific.add(btnRadioButtonSpecificTravel);
        btnSpecific.add(btnRadioButtonSpecificFree);
        btnSpecific.add(btnRadioButtonSpecificLong);
        btnSpecific.add(btnRadioButtonSpecificElse);

        Composite composite_Praise = new Composite(group_AddMarks, SWT.NONE);
        composite_Praise.setBounds(319, 499, 110, 123);

        text_Praise = new Text(composite_Praise, SWT.CENTER);
        text_Praise.setBounds(0, 0, 110, 20);
        text_Praise.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Praise.setEnabled(true);
        text_Praise.setEditable(false);
        text_Praise.setText("表彰奖励的新闻主体");

        Button btnRadioButtonPraiseGovern = new Button(composite_Praise, SWT.RADIO);
        btnRadioButtonPraiseGovern.setBounds(0, 26, 100, 17);
        btnRadioButtonPraiseGovern.setText("政府部门");

        Button btnRadioButtonPraiseFirm = new Button(composite_Praise, SWT.RADIO);
        btnRadioButtonPraiseFirm.setBounds(0, 46, 100, 17);
        btnRadioButtonPraiseFirm.setText("企业");

        Button btnRadioButtonPraiseUnits = new Button(composite_Praise, SWT.RADIO);
        btnRadioButtonPraiseUnits.setBounds(0, 66, 97, 17);
        btnRadioButtonPraiseUnits.setText("事业单位");

        Button btnRadioButtonPraiseInterest = new Button(composite_Praise, SWT.RADIO);
        btnRadioButtonPraiseInterest.setBounds(0, 86, 97, 17);
        btnRadioButtonPraiseInterest.setText("公益团体");

        Button btnRadioButtonPraiseIndividual = new Button(composite_Praise, SWT.RADIO);
        btnRadioButtonPraiseIndividual.setBounds(0, 106, 97, 17);
        btnRadioButtonPraiseIndividual.setText("个人");

        final ArrayList<Button> btnPraise = new ArrayList<>();
        btnPraise.add(btnRadioButtonPraiseGovern);
        btnPraise.add(btnRadioButtonPraiseFirm);
        btnPraise.add(btnRadioButtonPraiseUnits);
        btnPraise.add(btnRadioButtonPraiseInterest);
        btnPraise.add(btnRadioButtonPraiseIndividual);

        Composite composite_Sex = new Composite(group_AddMarks, SWT.NONE);
        composite_Sex.setBounds(10, 459, 106, 57);

        text_Sex = new Text(composite_Sex, SWT.CENTER);
        text_Sex.setBounds(0, 0, 62, 20);
        text_Sex.setText("性别");
        text_Sex.setEnabled(true);
        text_Sex.setEditable(false);
        text_Sex.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));

        Button btnRadioButtonSexMan = new Button(composite_Sex, SWT.RADIO);
        btnRadioButtonSexMan.setBounds(0, 20, 106, 17);
        btnRadioButtonSexMan.setText("男");

        Button btnRadioButtonSexWoman = new Button(composite_Sex, SWT.RADIO);
        btnRadioButtonSexWoman.setBounds(0, 40, 106, 17);
        btnRadioButtonSexWoman.setText("女");

        ArrayList<Button> btnSex = new ArrayList<>();
        btnSex.add(btnRadioButtonSexMan);
        btnSex.add(btnRadioButtonSexWoman);

        final ArrayList<ArrayList<Button>> btnMarks = new ArrayList<>();
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
        for (ArrayList<Button> markGroupType : btnMarks) {
            markGroupType.get(0).setSelection(true);
        }

        Button btnNewButton = new Button(group_AddMarks, SWT.NONE);
        String path="src/main/resources/Test1.xml";
        btnNewButton.addSelectionListener(new AddMarksListener(newsList, newsSummaryList, btnMarks,path));
        btnNewButton.setBounds(432, 586, 54, 20);
        btnNewButton.setText("Next");

        scrolledComposite.setContent(group_AddMarks);
        scrolledComposite.setMinSize(group_AddMarks.computeSize(SWT.DEFAULT, SWT.DEFAULT));

        return container;
    }

	/**
	 * Create the actions.
	 */
	private void createActions() {
          
	    
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		 ToolBarManager toolBarManager = new ToolBarManager(style);
	       
	        return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			TrainWindow window = new TrainWindow();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
        newShell.setText("News");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(1009, 734);
	}

}
