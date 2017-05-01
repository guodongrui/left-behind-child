package org.lbchild.window;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.forms.events.ExpansionAdapter;
import org.eclipse.ui.forms.events.ExpansionEvent;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import org.lbchild.chart.PieChart;
import org.lbchild.res.management.SWTResourceManager;

public class AnalysisWindow extends ApplicationWindow {
    private Action oriAction;
    private Action tenAction;
    private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());

    /**
     * Create the application window.
     */
    public AnalysisWindow() {
        super(null);
        createActions();
        addToolBar(SWT.FLAT | SWT.WRAP);
        addMenuBar();
        addStatusLine();
    }

    /**
     * Create contents of the application window.
     * 
     * @param parent
     */
    /*
     * (non-Javadoc)
     * 
     * @see
     * org.eclipse.jface.window.Window#createContents(org.eclipse.swt.widgets.
     * Composite)
     */
    @Override
    protected Control createContents(Composite parent) {

        Composite container = new Composite(parent, SWT.NONE);
        container.setLayout(new FormLayout());
        
        Section sctnSectionType = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionType = new FormData();
        fd_sctnSectionType.top = new FormAttachment(0);
        fd_sctnSectionType.left = new FormAttachment(0);
        sctnSectionType.setLayoutData(fd_sctnSectionType);
        formToolkit.paintBordersFor(sctnSectionType);
        sctnSectionType.setText("报纸类别");
        
        
        Composite composite_Type = formToolkit.createComposite(sctnSectionType);
        composite_Type.setLayout(new GridLayout());
        Button btnRadioButtonTypeCenter = formToolkit.createButton(composite_Type, "中央一级", SWT.RADIO);
        Button btnRadioButtonTypeProvince = formToolkit.createButton(composite_Type, "省一级", SWT.RADIO);
        Button btnRadioButtonTypeMarket = formToolkit.createButton(composite_Type, "经营模式市场化", SWT.RADIO);

        sctnSectionType.setClient(composite_Type);
        
        //-------------NType
        Section sctnSecionNType = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        fd_sctnSectionType.right = new FormAttachment(sctnSecionNType, 0, SWT.RIGHT);
        FormData fd_sctnSecionNType = new FormData();
        fd_sctnSecionNType.top = new FormAttachment(sctnSectionType);
        fd_sctnSecionNType.left = new FormAttachment(0);
        sctnSecionNType.setLayoutData(fd_sctnSecionNType);
        formToolkit.paintBordersFor(sctnSecionNType);
        sctnSecionNType.setText("新闻类型");
        
        Composite composite_NType = formToolkit.createComposite(sctnSecionNType);
        composite_NType.setLayout(new GridLayout());
        Button btnRadioButtonNTypePure = formToolkit.createButton(composite_NType, "纯净新闻", SWT.RADIO);
        Button btnRadioButtonNTypeFeature = formToolkit.createButton(composite_NType, "特稿与特写", SWT.RADIO);
        Button btnRadioButtonNTypeComment = formToolkit.createButton(composite_NType, "评论", SWT.RADIO);
        Button btnRadioButtonNTypeElse = formToolkit.createButton(composite_NType, "其他", SWT.RADIO);
        
        sctnSecionNType.setClient(composite_NType);
        
        // -----
        Section sctnSectionTheme = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionTheme = new FormData();
        fd_sctnSectionTheme.top = new FormAttachment(sctnSecionNType);
        fd_sctnSectionTheme.left = new FormAttachment(0);
        sctnSectionTheme.setLayoutData(fd_sctnSectionTheme);
        formToolkit.paintBordersFor(sctnSectionTheme);
        sctnSectionTheme.setText("报道主题");
        
        Composite composite_Theme = formToolkit.createComposite(sctnSectionTheme);
        composite_Theme.setLayout(new GridLayout());
        Button btnRadioButtonThemeHelp = formToolkit.createButton(composite_Theme, "社会各界帮助关爱", SWT.RADIO);
        Button btnRadioButtonThemeAdvice = formToolkit.createButton(composite_Theme, "社会各界对留守儿童现象提出的建议和看法", SWT.RADIO);
        Button btnRadioButtonThemePraise = formToolkit.createButton(composite_Theme, "表彰帮助关爱留守儿童的单位或个人", SWT.RADIO);
        Button btnRadioButtonThemeViolent = formToolkit.createButton(composite_Theme, "留守儿童遭受暴力", SWT.RADIO);
        Button btnRadioButtonThemeAbuse = formToolkit.createButton(composite_Theme, "留守儿童被性侵、猥亵、强奸或是怀孕、生子等", SWT.RADIO);
        Button btnRadioButtonThemeCriminal = formToolkit.createButton(composite_Theme, "留守儿童犯罪等", SWT.RADIO);
        Button btnRadioButtonThemeDeath = formToolkit.createButton(composite_Theme, "留守儿童意外死亡", SWT.RADIO);
        Button btnRadioButtonThemeEffort = formToolkit.createButton(composite_Theme, "留守儿童努力上进", SWT.RADIO);
        Button btnRadioButtonThemeRough = formToolkit.createButton(composite_Theme, "打工父母在城市艰难生活", SWT.RADIO);
        Button btnRadioButtonThemeElse = formToolkit.createButton(composite_Theme, "其他", SWT.RADIO);
        
        sctnSectionTheme.setClient(composite_Theme);
        
        //-------
        Section sctnSectionSource = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionSource = new FormData();
        fd_sctnSectionSource.left = new FormAttachment(0);
        fd_sctnSectionSource.top = new FormAttachment(sctnSectionTheme, 1);
        sctnSectionSource.setLayoutData(fd_sctnSectionSource);
        formToolkit.paintBordersFor(sctnSectionSource);
        sctnSectionSource.setText("新闻报道来源");
        sctnSectionSource.setExpanded(true);
        
        Composite composite_Source = formToolkit.createComposite(sctnSectionSource);
        composite_Source.setLayout(new GridLayout());
        Button btnRadioButtonSourceReporter = formToolkit.createButton(composite_Source, "记者", SWT.RADIO);
        Button btnRadioButtonSourceGovern = formToolkit.createButton(composite_Source, "政府", SWT.RADIO);
        Button btnRadioButtonSourceInterest = formToolkit.createButton(composite_Source, "公益团体", SWT.RADIO);
        Button btnRadioButtonSourceExpert = formToolkit.createButton(composite_Source, "专家学者", SWT.RADIO);
        Button btnRadioButtonSourceFirm = formToolkit.createButton(composite_Source, "企业", SWT.RADIO);
        Button btnRadioButtonSourceUnits = formToolkit.createButton(composite_Source, "事业单位", SWT.RADIO);
        Button btnRadioButtonSourceLeader = formToolkit.createButton(composite_Source, "政府领导、政协或人大代表", SWT.RADIO);
        Button btnRadioButtonSourceElse = formToolkit.createButton(composite_Source, "其他", SWT.RADIO);
        
        sctnSectionSource.setClient(composite_Source);
        
        //--------
        Section sctnSectionImage = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionImage = new FormData();
        fd_sctnSectionImage.right = new FormAttachment(sctnSectionType, 0, SWT.RIGHT);
        fd_sctnSectionImage.left = new FormAttachment(0);
        fd_sctnSectionImage.top = new FormAttachment(sctnSectionSource);
        sctnSectionImage.setLayoutData(fd_sctnSectionImage);
        formToolkit.paintBordersFor(sctnSectionImage);
        sctnSectionImage.setText("媒体呈现形象");
        
        Composite composite_Image = formToolkit.createComposite(sctnSectionImage);
        composite_Image.setLayout(new GridLayout());
        Button btnRadioButtonImagePositive = formToolkit.createButton(composite_Image, "积极健康", SWT.RADIO);
        Button btnRadioButtonImagePoor = formToolkit.createButton(composite_Image, "可伶悲惨", SWT.RADIO);
        Button btnRadioButtonImageHappiness = formToolkit.createButton(composite_Image, "沫恩幸福", SWT.RADIO);
        Button btnRadioButtonImageProblem = formToolkit.createButton(composite_Image, "问题儿童", SWT.RADIO);
        Button btnRadioButtonImageElse = formToolkit.createButton(composite_Image, "其他", SWT.RADIO);
        
        sctnSectionImage.setClient(composite_Image);
        
        //-----
        Section sctnSectionSpecific = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionSpecific = new FormData();
        fd_sctnSectionSpecific.top = new FormAttachment(sctnSectionImage);
        fd_sctnSectionSpecific.right = new FormAttachment(sctnSectionType, 0, SWT.RIGHT);
        fd_sctnSectionSpecific.left = new FormAttachment(0);
        sctnSectionSpecific.setLayoutData(fd_sctnSectionSpecific);
        formToolkit.paintBordersFor(sctnSectionSpecific);
        sctnSectionSpecific.setText("帮助新闻的具体种类");
        
        Composite composite_Specific = formToolkit.createComposite(sctnSectionSpecific);
        composite_Specific.setLayout(new GridLayout());
        Button btnRadioButtonSpecificDonate = formToolkit.createButton(composite_Specific, "单纯一次捐款捐物", SWT.RADIO);
        Button btnRadioButtonSpecificTravel = formToolkit.createButton(composite_Specific, "旅游活动安排的项目之一", SWT.RADIO);
        Button btnRadioButtonSpecificFree = formToolkit.createButton(composite_Specific, "免费开放", SWT.RADIO);
        Button btnRadioButtonSpecificLong = formToolkit.createButton(composite_Specific, "设立长期资助项目", SWT.RADIO);
        Button btnRadioButtonSpecificElse = formToolkit.createButton(composite_Specific, "其他", SWT.RADIO);
        
        sctnSectionSpecific.setClient(composite_Specific);  
        
        //-------
        Section sctnSectionSubject = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        fd_sctnSectionSpecific.bottom = new FormAttachment(sctnSectionSubject);
        FormData fd_sctnSectionSubject = new FormData();
        fd_sctnSectionSubject.right = new FormAttachment(sctnSectionType, 0, SWT.RIGHT);
        fd_sctnSectionSubject.top = new FormAttachment(0, 335);
        fd_sctnSectionSubject.left = new FormAttachment(0);
        sctnSectionSubject.setLayoutData(fd_sctnSectionSubject);
        formToolkit.paintBordersFor(sctnSectionSubject);
        sctnSectionSubject.setText("帮助类新闻的主体");
        
        Composite composite_Subject = formToolkit.createComposite(sctnSectionSubject);
        composite_Subject.setLayout(new GridLayout());
        Button btnRadioButtonSubjectGovern = formToolkit.createButton(composite_Subject, "政府部门", SWT.RADIO);
        Button btnRadioButtonSubjectInterest = formToolkit.createButton(composite_Subject, "公益团体", SWT.RADIO);
        Button btnRadioButtonSubjectFirm = formToolkit.createButton(composite_Subject, "企业", SWT.RADIO);
        Button btnRadioButtonSubjectUnits = formToolkit.createButton(composite_Subject, "事业单位", SWT.RADIO);
        Button btnRadioButtonSubjectIndividual = formToolkit.createButton(composite_Subject, "个人", SWT.RADIO);
 
        sctnSectionSubject.setClient(composite_Subject);  
        
        //---------
        Section sctnSectionPraise = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionPraise = new FormData();
        fd_sctnSectionPraise.right = new FormAttachment(sctnSectionType, 0, SWT.RIGHT);
        fd_sctnSectionPraise.top = new FormAttachment(sctnSectionSubject, 1);
        fd_sctnSectionPraise.left = new FormAttachment(0);
        sctnSectionPraise.setLayoutData(fd_sctnSectionPraise);
        formToolkit.paintBordersFor(sctnSectionPraise);
        sctnSectionPraise.setText("表彰奖励的新闻主体");
        
        Composite composite_Praise = formToolkit.createComposite(sctnSectionPraise);
        composite_Praise.setLayout(new GridLayout());
        Button btnRadioButtonPraiseGovern = formToolkit.createButton(composite_Praise, "政府部门", SWT.RADIO);
        Button btnRadioButtonPraiseInterest = formToolkit.createButton(composite_Praise, "公益团体", SWT.RADIO);
        Button btnRadioButtonPraiseFirm = formToolkit.createButton(composite_Praise, "企业", SWT.RADIO);
        Button btnRadioButtonPraiseUnits = formToolkit.createButton(composite_Praise, "事业单位", SWT.RADIO);
        Button btnRadioButtonPraiseIndividual = formToolkit.createButton(composite_Praise, "个人", SWT.RADIO);
        
        sctnSectionPraise.setClient(composite_Praise); 
    
        //------------
        Section sctnSectionReason = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        fd_sctnSectionTheme.right = new FormAttachment(sctnSectionReason, 0, SWT.RIGHT);
        FormData fd_sctnSectionReason = new FormData();
        fd_sctnSectionReason.top = new FormAttachment(sctnSectionPraise, 1);
        fd_sctnSectionReason.left = new FormAttachment(0);
        sctnSectionReason.setLayoutData(fd_sctnSectionReason);
        formToolkit.paintBordersFor(sctnSectionReason);
        sctnSectionReason.setText("农民工子女不能留在城市读书的原因");
        
        Composite composite_Reason = formToolkit.createComposite(sctnSectionReason);
        composite_Reason.setLayout(new GridLayout());
        Button btnRadioButtonReasonRegister = formToolkit.createButton(composite_Reason, "无本地户籍难入公立学校", SWT.RADIO);
        Button btnRadioButtonReasonFee = formToolkit.createButton(composite_Reason, "私立学校学费高", SWT.RADIO);
        Button btnRadioButtonReasonQuality = formToolkit.createButton(composite_Reason, "私立学校教学质量没保障", SWT.RADIO);
        Button btnRadioButtonReasonCancel = formToolkit.createButton(composite_Reason, "越来越多的小型私立学校被国家取消办学资格", SWT.RADIO);
        Button btnRadioButtonReasonElse = formToolkit.createButton(composite_Reason, "其他", SWT.RADIO);
        
        sctnSectionReason.setClient(composite_Reason); 
        
        //----------
        Composite composite = new Composite(container, SWT.NONE);
        fd_sctnSectionSource.right = new FormAttachment(composite, -4);
        fd_sctnSecionNType.right = new FormAttachment(composite, -4);
        FormData fd_composite = new FormData();
        fd_composite.bottom = new FormAttachment(100, -22);
        fd_composite.right = new FormAttachment(sctnSectionReason, 466, SWT.RIGHT);
        fd_composite.left = new FormAttachment(sctnSectionReason, 4);
        fd_composite.top = new FormAttachment(0, 5);
        composite.setLayoutData(fd_composite);
        formToolkit.adapt(composite);
        formToolkit.paintBordersFor(composite);
        
        Map<String, Integer> map = new HashMap<>();
        map.put("男", 7);
        map.put("女", 3);
        PieChart pieChart = new PieChart(map);
        pieChart.setTile("测试工作");
        ChartComposite chartComposite = new ChartComposite(composite, SWT.NONE, pieChart.createChart(), true);
        chartComposite.setBounds(53, 10, 360, 267);

    

        return container;
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        // Create the actions
        {
            oriAction = new Action("Orientation") {

            };
        }
        {
            tenAction = new Action("Tendency") {

            };
        }
    }

    /**
     * Create the menu manager.
     * 
     * @return the menu manager
     */
    @Override
    protected MenuManager createMenuManager() {
        MenuManager menuManager = new MenuManager("menu");
        return menuManager;
    }

    /**
     * Create the toolbar manager.
     * 
     * @return the toolbar manager
     */
    @Override
    protected ToolBarManager createToolBarManager(int style) {
        ToolBarManager toolBarManager = new ToolBarManager(style);
        toolBarManager.add(oriAction);
        toolBarManager.add(tenAction);
        return toolBarManager;
    }

    /**
     * Create the status line manager.
     * 
     * @return the status line manager
     */
    @Override
    protected StatusLineManager createStatusLineManager() {
        StatusLineManager statusLineManager = new StatusLineManager();
        return statusLineManager;
    }

    /**
     * Launch the application.
     * 
     * @param args
     */
    public static void main(String args[]) {
        try {
            AnalysisWindow window = new AnalysisWindow();
            window.setBlockOnOpen(true);
            window.open();
            Display.getCurrent().dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Configure the shell.
     * 
     * @param newShell
     */
    @Override
    protected void configureShell(Shell newShell) {
        super.configureShell(newShell);
        newShell.setText("Analysis");
    }

    /**
     * Return the initial size of the window.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(800, 500);
    }
}
