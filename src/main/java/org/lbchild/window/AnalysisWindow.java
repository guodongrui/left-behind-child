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
import org.eclipse.swt.widgets.Button;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;

import org.lbchild.chart.LineChart;
import org.lbchild.chart.PieChart;
import org.lbchild.controller.ExpandCleanMarksListener;
import org.lbchild.controller.ShowOrientationListener;
import org.lbchild.controller.ShowSexOrientationListener;
import org.lbchild.controller.ShowSexTendencyListener;
import org.lbchild.util.CountLabel;
import org.lbchild.xml.XMLReader;



public class AnalysisWindow extends ApplicationWindow {
    
    private final FormToolkit formToolkit = new FormToolkit(Display.getDefault());
    private ArrayList<Map<String, Integer>> newsmarks;
    private int[][][] sexOrientationCount;
    private double[][][][] sexTendencyCount;

    Section sctnSectionTypeOrientation;

    /**
     * Create the application window.
     */
    public AnalysisWindow() {
        super(null);
        initDataset();
        createActions();
        addToolBar(SWT.FLAT | SWT.WRAP);
        addMenuBar();
        addStatusLine();
    }
    public Section getSection() {
    	return sctnSectionTypeOrientation;
		
	}
    void initDataset() {
        File file = new File("src/main/resources/newsmarks.xml");
        XMLReader in = new XMLReader(file);
        ArrayList<Map<String, String>> arr = in.readXml();
        
        CountLabel cl = new CountLabel(arr);
        
        newsmarks = cl.getLabelCount(); 
        
        sexOrientationCount = cl.getLabelSexCount();
        
        sexTendencyCount = cl.getLabelDateCount();
        
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
        container.setLayout(new FillLayout());

        CTabFolder tabFolder = new CTabFolder(container, SWT.BORDER);
        tabFolder.setSelectionBackground(
                Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
        tabFolder.setLayout(new FillLayout());

        /*
         * Orientation
         */
        Composite compositeOrientation = new Composite(tabFolder, SWT.NONE);
        compositeOrientation.setLayout(new FillLayout());

        Composite marksOrientationComposite = new Composite(compositeOrientation, SWT.NONE);
        marksOrientationComposite.setLayout(new FormLayout());

        Composite orientationPieChartComposite = new Composite(compositeOrientation, SWT.NONE);

        formToolkit.adapt(orientationPieChartComposite);
        formToolkit.paintBordersFor(orientationPieChartComposite);
        orientationPieChartComposite.setLayout(new FillLayout());

        CTabItem item_orientation = new CTabItem(tabFolder, SWT.NONE);
        item_orientation.setText("Orientation");
        item_orientation.setControl(compositeOrientation);

       sctnSectionTypeOrientation = formToolkit.createSection(marksOrientationComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        

        FormData fd_sctnSectionTypeOrientation = new FormData();
        fd_sctnSectionTypeOrientation.top = new FormAttachment(0);
        fd_sctnSectionTypeOrientation.left = new FormAttachment(0);
        sctnSectionTypeOrientation.setLayoutData(fd_sctnSectionTypeOrientation);
        formToolkit.paintBordersFor(sctnSectionTypeOrientation);
        sctnSectionTypeOrientation.setText("报纸类别");

        Composite composite_TypeOrientation = formToolkit.createComposite(sctnSectionTypeOrientation);
        composite_TypeOrientation.setLayout(new GridLayout());
        Button btnRadioButtonTypeOrientationCenter = formToolkit.createButton(composite_TypeOrientation, "中央一级",
                SWT.RADIO);
        Button btnRadioButtonTypeOrientationProvince = formToolkit.createButton(composite_TypeOrientation, "省一级",
                SWT.RADIO);
        Button btnRadioButtonTypeOrientationMarket = formToolkit.createButton(composite_TypeOrientation, "经营模式市场化",
                SWT.RADIO);
        
        sctnSectionTypeOrientation.setClient(composite_TypeOrientation);

        Section sctnSectionNTypeOrientation = formToolkit.createSection(marksOrientationComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionNTypeOrientation = new FormData();
        fd_sctnSectionNTypeOrientation.top = new FormAttachment(sctnSectionTypeOrientation);
        fd_sctnSectionNTypeOrientation.left = new FormAttachment(0);
        sctnSectionNTypeOrientation.setLayoutData(fd_sctnSectionNTypeOrientation);
        formToolkit.paintBordersFor(sctnSectionNTypeOrientation);
        sctnSectionNTypeOrientation.setText("新闻类型");

        Composite composite_NTypeOrientation = formToolkit.createComposite(sctnSectionNTypeOrientation);
        composite_NTypeOrientation.setLayout(new GridLayout());
        Button btnRadioButtonNTypeOrientationPure = formToolkit.createButton(composite_NTypeOrientation, "纯净新闻",
                SWT.RADIO);
        Button btnRadioButtonNTypeOrientationFeature = formToolkit.createButton(composite_NTypeOrientation, "特稿与特写",
                SWT.RADIO);
        Button btnRadioButtonNTypeOrientationComment = formToolkit.createButton(composite_NTypeOrientation, "评论",
                SWT.RADIO);
        Button btnRadioButtonNTypeOrientationElse = formToolkit.createButton(composite_NTypeOrientation, "其他",
                SWT.RADIO);

        sctnSectionNTypeOrientation.setClient(composite_NTypeOrientation);

        Section sctnSectionThemeOrientation = formToolkit.createSection(marksOrientationComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionThemeOrientation = new FormData();
        fd_sctnSectionThemeOrientation.top = new FormAttachment(sctnSectionNTypeOrientation);
        fd_sctnSectionThemeOrientation.left = new FormAttachment(0);
        sctnSectionThemeOrientation.setLayoutData(fd_sctnSectionThemeOrientation);
        formToolkit.paintBordersFor(sctnSectionThemeOrientation);
        sctnSectionThemeOrientation.setText("报道主题");

        Composite composite_ThemeOrientation = formToolkit.createComposite(sctnSectionThemeOrientation);
        composite_ThemeOrientation.setLayout(new GridLayout());
        Button btnRadioButtonThemeOrientationHelp = formToolkit.createButton(composite_ThemeOrientation, "社会各界帮助关爱",
                SWT.RADIO);
        Button btnRadioButtonThemeOrientationAdvice = formToolkit.createButton(composite_ThemeOrientation,
                "社会各界对留守儿童现象提出的建议和看法", SWT.RADIO);
        Button btnRadioButtonThemeOrientationPraise = formToolkit.createButton(composite_ThemeOrientation,
                "表彰帮助关爱留守儿童的单位或个人", SWT.RADIO);
        Button btnRadioButtonThemeOrientationViolent = formToolkit.createButton(composite_ThemeOrientation, "留守儿童遭受暴力",
                SWT.RADIO);
        Button btnRadioButtonThemeOrientationAbuse = formToolkit.createButton(composite_ThemeOrientation,
                "留守儿童被性侵、猥亵、强奸或是怀孕、生子等", SWT.RADIO);
        Button btnRadioButtonThemeOrientationCriminal = formToolkit.createButton(composite_ThemeOrientation, "留守儿童犯罪等",
                SWT.RADIO);
        Button btnRadioButtonThemeOrientationDeath = formToolkit.createButton(composite_ThemeOrientation, "留守儿童意外死亡",
                SWT.RADIO);
        Button btnRadioButtonThemeOrientationEffort = formToolkit.createButton(composite_ThemeOrientation, "留守儿童努力上进",
                SWT.RADIO);
        Button btnRadioButtonThemeOrientationRough = formToolkit.createButton(composite_ThemeOrientation, "打工父母在城市艰难生活",
                SWT.RADIO);
        Button btnRadioButtonThemeOrientationElse = formToolkit.createButton(composite_ThemeOrientation, "其他",
                SWT.RADIO);

        sctnSectionThemeOrientation.setClient(composite_ThemeOrientation);

        Section sctnSectionSourceOrientation = formToolkit.createSection(marksOrientationComposite,
                Section.TWISTIE | Section.TITLE_BAR);
       
        FormData fd_sctnSectionSourceOrientation = new FormData();
        fd_sctnSectionSourceOrientation.top = new FormAttachment(sctnSectionThemeOrientation, 1);
        fd_sctnSectionSourceOrientation.left = new FormAttachment(0);
        sctnSectionSourceOrientation.setLayoutData(fd_sctnSectionSourceOrientation);
        formToolkit.paintBordersFor(sctnSectionSourceOrientation);
        sctnSectionSourceOrientation.setText("新闻报道来源");

        Composite composite_SourceOrientation = formToolkit.createComposite(sctnSectionSourceOrientation);
        composite_SourceOrientation.setLayout(new GridLayout());
        Button btnRadioButtonSourceOrientationReporter = formToolkit.createButton(composite_SourceOrientation, "记者",
                SWT.RADIO);
        Button btnRadioButtonSourceOrientationGovern = formToolkit.createButton(composite_SourceOrientation, "政府",
                SWT.RADIO);
        Button btnRadioButtonSourceOrientationInterest = formToolkit.createButton(composite_SourceOrientation, "公益团体",
                SWT.RADIO);
        Button btnRadioButtonSourceOrientationExpert = formToolkit.createButton(composite_SourceOrientation, "专家学者",
                SWT.RADIO);
        Button btnRadioButtonSourceOrientationFirm = formToolkit.createButton(composite_SourceOrientation, "企业",
                SWT.RADIO);
        Button btnRadioButtonSourceOrientationUnits = formToolkit.createButton(composite_SourceOrientation, "事业单位",
                SWT.RADIO);
        Button btnRadioButtonSourceOrientationLeader = formToolkit.createButton(composite_SourceOrientation,
                "政府领导、政协或人大代表", SWT.RADIO);
        Button btnRadioButtonSourceOrientationElse = formToolkit.createButton(composite_SourceOrientation, "其他",
                SWT.RADIO);

        sctnSectionSourceOrientation.setClient(composite_SourceOrientation);

        Section sctnSectionImageOrientation = formToolkit.createSection(marksOrientationComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionImageOrientation = new FormData();
        fd_sctnSectionImageOrientation.top = new FormAttachment(sctnSectionSourceOrientation);
        fd_sctnSectionImageOrientation.left = new FormAttachment(0);
        sctnSectionImageOrientation.setLayoutData(fd_sctnSectionImageOrientation);
        formToolkit.paintBordersFor(sctnSectionImageOrientation);
        sctnSectionImageOrientation.setText("媒体呈现形象");

        Composite composite_ImageOrientation = formToolkit.createComposite(sctnSectionImageOrientation);
        composite_ImageOrientation.setLayout(new GridLayout());
        Button btnRadioButtonImageOrientationPositive = formToolkit.createButton(composite_ImageOrientation, "积极健康",
                SWT.RADIO);
        Button btnRadioButtonImageOrientationPoor = formToolkit.createButton(composite_ImageOrientation, "可伶悲惨",
                SWT.RADIO);
        Button btnRadioButtonImageOrientationHappiness = formToolkit.createButton(composite_ImageOrientation, "沫恩幸福",
                SWT.RADIO);
        Button btnRadioButtonImageOrientationProblem = formToolkit.createButton(composite_ImageOrientation, "问题儿童",
                SWT.RADIO);
        Button btnRadioButtonImageOrientationElse = formToolkit.createButton(composite_ImageOrientation, "其他",
                SWT.RADIO);

        sctnSectionImageOrientation.setClient(composite_ImageOrientation);

        Section sctnSectionSpecificOrientation = formToolkit.createSection(marksOrientationComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionSpecificOrientation = new FormData();
        fd_sctnSectionSpecificOrientation.top = new FormAttachment(sctnSectionImageOrientation);
        fd_sctnSectionSpecificOrientation.left = new FormAttachment(0);
        sctnSectionSpecificOrientation.setLayoutData(fd_sctnSectionSpecificOrientation);
        formToolkit.paintBordersFor(sctnSectionSpecificOrientation);
        sctnSectionSpecificOrientation.setText("帮助新闻的具体种类");

        Composite composite_SpecificOrientation = formToolkit.createComposite(sctnSectionSpecificOrientation);
        composite_SpecificOrientation.setLayout(new GridLayout());
        Button btnRadioButtonSpecificOrientationDonate = formToolkit.createButton(composite_SpecificOrientation,
                "单纯一次捐款捐物", SWT.RADIO);
        Button btnRadioButtonSpecificOrientationTravel = formToolkit.createButton(composite_SpecificOrientation,
                "旅游活动安排的项目之一", SWT.RADIO);
        Button btnRadioButtonSpecificOrientationFree = formToolkit.createButton(composite_SpecificOrientation, "免费开放",
                SWT.RADIO);
        Button btnRadioButtonSpecificOrientationLong = formToolkit.createButton(composite_SpecificOrientation,
                "设立长期资助项目", SWT.RADIO);
        Button btnRadioButtonSpecificOrientationElse = formToolkit.createButton(composite_SpecificOrientation, "其他",
                SWT.RADIO);

        sctnSectionSpecificOrientation.setClient(composite_SpecificOrientation);

        Section sctnSectionSubjectOrientation = formToolkit.createSection(marksOrientationComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionSubjectOrientation = new FormData();
        fd_sctnSectionSubjectOrientation.top = new FormAttachment(sctnSectionSpecificOrientation);
        fd_sctnSectionSubjectOrientation.left = new FormAttachment(0);
        sctnSectionSubjectOrientation.setLayoutData(fd_sctnSectionSubjectOrientation);
        formToolkit.paintBordersFor(sctnSectionSubjectOrientation);
        sctnSectionSubjectOrientation.setText("帮助类新闻的主体");

        Composite composite_SubjectOrientation = formToolkit.createComposite(sctnSectionSubjectOrientation);
        composite_SubjectOrientation.setLayout(new GridLayout());
        Button btnRadioButtonSubjectOrientationGovern = formToolkit.createButton(composite_SubjectOrientation, "政府部门",
                SWT.RADIO);
        Button btnRadioButtonSubjectOrientationInterest = formToolkit.createButton(composite_SubjectOrientation, "公益团体",
                SWT.RADIO);
        Button btnRadioButtonSubjectOrientationFirm = formToolkit.createButton(composite_SubjectOrientation, "企业",
                SWT.RADIO);
        Button btnRadioButtonSubjectOrientationUnits = formToolkit.createButton(composite_SubjectOrientation, "事业单位",
                SWT.RADIO);
        Button btnRadioButtonSubjectOrientationIndividual = formToolkit.createButton(composite_SubjectOrientation, "个人",
                SWT.RADIO);

        sctnSectionSubjectOrientation.setClient(composite_SubjectOrientation);

        Section sctnSectionPraiseOrientation = formToolkit.createSection(marksOrientationComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionPraiseOrientation = new FormData();
        fd_sctnSectionPraiseOrientation.top = new FormAttachment(sctnSectionSubjectOrientation, 1);
        fd_sctnSectionPraiseOrientation.left = new FormAttachment(0);
        sctnSectionPraiseOrientation.setLayoutData(fd_sctnSectionPraiseOrientation);
        formToolkit.paintBordersFor(sctnSectionPraiseOrientation);
        sctnSectionPraiseOrientation.setText("表彰奖励的新闻主体");

        Composite composite_PraiseOrientation = formToolkit.createComposite(sctnSectionPraiseOrientation);
        composite_PraiseOrientation.setLayout(new GridLayout());
        Button btnRadioButtonPraiseOrientationGovern = formToolkit.createButton(composite_PraiseOrientation, "政府部门",
                SWT.RADIO);
        Button btnRadioButtonPraiseOrientationInterest = formToolkit.createButton(composite_PraiseOrientation, "公益团体",
                SWT.RADIO);
        Button btnRadioButtonPraiseOrientationFirm = formToolkit.createButton(composite_PraiseOrientation, "企业",
                SWT.RADIO);
        Button btnRadioButtonPraiseOrientationUnits = formToolkit.createButton(composite_PraiseOrientation, "事业单位",
                SWT.RADIO);
        Button btnRadioButtonPraiseOrientationIndividual = formToolkit.createButton(composite_PraiseOrientation, "个人",
                SWT.RADIO);

        sctnSectionPraiseOrientation.setClient(composite_PraiseOrientation);

        Section sctnSectionReasonOrientation = formToolkit.createSection(marksOrientationComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionReasonOrientation = new FormData();
        fd_sctnSectionReasonOrientation.top = new FormAttachment(sctnSectionPraiseOrientation, 1);
        fd_sctnSectionReasonOrientation.left = new FormAttachment(0);
        sctnSectionReasonOrientation.setLayoutData(fd_sctnSectionReasonOrientation);
        formToolkit.paintBordersFor(sctnSectionReasonOrientation);
        sctnSectionReasonOrientation.setText("农民工子女不能留在城市读书的原因");

        Composite composite_ReasonOrientation = formToolkit.createComposite(sctnSectionReasonOrientation);
        composite_ReasonOrientation.setLayout(new GridLayout());
        Button btnRadioButtonReasonOrientationRegister = formToolkit.createButton(composite_ReasonOrientation,
                "无本地户籍难入公立学校", SWT.RADIO);
        Button btnRadioButtonReasonOrientationFee = formToolkit.createButton(composite_ReasonOrientation, "私立学校学费高",
                SWT.RADIO);
        Button btnRadioButtonReasonOrientationQuality = formToolkit.createButton(composite_ReasonOrientation,
                "私立学校教学质量没保障", SWT.RADIO);
        Button btnRadioButtonReasonOrientationCancel = formToolkit.createButton(composite_ReasonOrientation,
                "越来越多的小型私立学校被国家取消办学资格", SWT.RADIO);
        Button btnRadioButtonReasonOrientationElse = formToolkit.createButton(composite_ReasonOrientation, "其他",
                SWT.RADIO);

        sctnSectionReasonOrientation.setClient(composite_ReasonOrientation);

        // set all formData right align to Theme
        fd_sctnSectionTypeOrientation.right = new FormAttachment(sctnSectionThemeOrientation, 0, SWT.RIGHT);
        fd_sctnSectionNTypeOrientation.right = new FormAttachment(sctnSectionThemeOrientation, 0, SWT.RIGHT);
        fd_sctnSectionSourceOrientation.right = new FormAttachment(sctnSectionThemeOrientation, 0, SWT.RIGHT);
        fd_sctnSectionImageOrientation.right = new FormAttachment(sctnSectionThemeOrientation, 0, SWT.RIGHT);
        fd_sctnSectionSpecificOrientation.right = new FormAttachment(sctnSectionThemeOrientation, 0, SWT.RIGHT);
        fd_sctnSectionSubjectOrientation.right = new FormAttachment(sctnSectionThemeOrientation, 0, SWT.RIGHT);
        fd_sctnSectionPraiseOrientation.right = new FormAttachment(sctnSectionThemeOrientation, 0, SWT.RIGHT);
        fd_sctnSectionReasonOrientation.right = new FormAttachment(sctnSectionThemeOrientation, 0, SWT.RIGHT);

        /*
         * Tendency
         */
        Composite compositeTendency = new Composite(tabFolder, SWT.NONE);
        compositeTendency.setLayout(new FillLayout());

        Composite marksTendencyComposite = new Composite(compositeTendency, SWT.NONE);
        marksTendencyComposite.setLayout(new FormLayout());

        Composite tendencyLineChartComposite = new Composite(compositeTendency, SWT.NONE);
        formToolkit.adapt(tendencyLineChartComposite);
        formToolkit.paintBordersFor(tendencyLineChartComposite);
        tendencyLineChartComposite.setLayout(new FillLayout());

        CTabItem item_tendency = new CTabItem(tabFolder, SWT.BORDER);
        item_tendency.setText("Tendency");
        item_tendency.setControl(compositeTendency);

        Section sctnSectionTypeTendency = formToolkit.createSection(marksTendencyComposite,
                Section.TWISTIE | Section.TITLE_BAR);

       

        FormData fd_sctnSectionTypeTendency = new FormData();
        fd_sctnSectionTypeTendency.top = new FormAttachment(0);
        fd_sctnSectionTypeTendency.left = new FormAttachment(0);
        sctnSectionTypeTendency.setLayoutData(fd_sctnSectionTypeTendency);
        formToolkit.paintBordersFor(sctnSectionTypeTendency);
        sctnSectionTypeTendency.setText("报纸类别");

        Composite composite_TypeTendency = formToolkit.createComposite(sctnSectionTypeTendency);
        composite_TypeTendency.setLayout(new GridLayout());
        Button btnRadioButtonTypeTendencyCenter = formToolkit.createButton(composite_TypeTendency, "中央一级", SWT.RADIO);
        Button btnRadioButtonTypeTendencyProvince = formToolkit.createButton(composite_TypeTendency, "省一级", SWT.RADIO);
        Button btnRadioButtonTypeTendencyMarket = formToolkit.createButton(composite_TypeTendency, "经营模式市场化",
                SWT.RADIO);

        sctnSectionTypeTendency.setClient(composite_TypeTendency);

        Section sctnSectionNTypeTendency = formToolkit.createSection(marksTendencyComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionNTypeTendency = new FormData();
        fd_sctnSectionNTypeTendency.top = new FormAttachment(sctnSectionTypeTendency);
        fd_sctnSectionNTypeTendency.left = new FormAttachment(0);
        sctnSectionNTypeTendency.setLayoutData(fd_sctnSectionNTypeTendency);
        formToolkit.paintBordersFor(sctnSectionNTypeTendency);
        sctnSectionNTypeTendency.setText("新闻类型");

        Composite composite_NTypeTendency = formToolkit.createComposite(sctnSectionNTypeTendency);
        composite_NTypeTendency.setLayout(new GridLayout());
        Button btnRadioButtonNTypeTendencyPure = formToolkit.createButton(composite_NTypeTendency, "纯净新闻", SWT.RADIO);
        Button btnRadioButtonNTypeTendencyFeature = formToolkit.createButton(composite_NTypeTendency, "特稿与特写",
                SWT.RADIO);
        Button btnRadioButtonNTypeTendencyComment = formToolkit.createButton(composite_NTypeTendency, "评论", SWT.RADIO);
        Button btnRadioButtonNTypeTendencyElse = formToolkit.createButton(composite_NTypeTendency, "其他", SWT.RADIO);

        sctnSectionNTypeTendency.setClient(composite_NTypeTendency);

        Section sctnSectionThemeTendency = formToolkit.createSection(marksTendencyComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionThemeTendency = new FormData();
        fd_sctnSectionThemeTendency.top = new FormAttachment(sctnSectionNTypeTendency);
        fd_sctnSectionThemeTendency.left = new FormAttachment(0);
        sctnSectionThemeTendency.setLayoutData(fd_sctnSectionThemeTendency);
        formToolkit.paintBordersFor(sctnSectionThemeTendency);
        sctnSectionThemeTendency.setText("报道主题");

        Composite composite_ThemeTendency = formToolkit.createComposite(sctnSectionThemeTendency);
        composite_ThemeTendency.setLayout(new GridLayout());
        Button btnRadioButtonThemeTendencyHelp = formToolkit.createButton(composite_ThemeTendency, "社会各界帮助关爱",
                SWT.RADIO);
        Button btnRadioButtonThemeTendencyAdvice = formToolkit.createButton(composite_ThemeTendency,
                "社会各界对留守儿童现象提出的建议和看法", SWT.RADIO);
        Button btnRadioButtonThemeTendencyPraise = formToolkit.createButton(composite_ThemeTendency, "表彰帮助关爱留守儿童的单位或个人",
                SWT.RADIO);
        Button btnRadioButtonThemeTendencyViolent = formToolkit.createButton(composite_ThemeTendency, "留守儿童遭受暴力",
                SWT.RADIO);
        Button btnRadioButtonThemeTendencyAbuse = formToolkit.createButton(composite_ThemeTendency,
                "留守儿童被性侵、猥亵、强奸或是怀孕、生子等", SWT.RADIO);
        Button btnRadioButtonThemeTendencyCriminal = formToolkit.createButton(composite_ThemeTendency, "留守儿童犯罪等",
                SWT.RADIO);
        Button btnRadioButtonThemeTendencyDeath = formToolkit.createButton(composite_ThemeTendency, "留守儿童意外死亡",
                SWT.RADIO);
        Button btnRadioButtonThemeTendencyEffort = formToolkit.createButton(composite_ThemeTendency, "留守儿童努力上进",
                SWT.RADIO);
        Button btnRadioButtonThemeTendencyRough = formToolkit.createButton(composite_ThemeTendency, "打工父母在城市艰难生活",
                SWT.RADIO);
        Button btnRadioButtonThemeTendencyElse = formToolkit.createButton(composite_ThemeTendency, "其他", SWT.RADIO);

        sctnSectionThemeTendency.setClient(composite_ThemeTendency);

        Section sctnSectionSourceTendency = formToolkit.createSection(marksTendencyComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionSourceTendency = new FormData();
        fd_sctnSectionSourceTendency.top = new FormAttachment(sctnSectionThemeTendency, 1);
        fd_sctnSectionSourceTendency.left = new FormAttachment(0);
        sctnSectionSourceTendency.setLayoutData(fd_sctnSectionSourceTendency);
        formToolkit.paintBordersFor(sctnSectionSourceTendency);
        sctnSectionSourceTendency.setText("新闻报道来源");

        Composite composite_SourceTendency = formToolkit.createComposite(sctnSectionSourceTendency);
        composite_SourceTendency.setLayout(new GridLayout());
        Button btnRadioButtonSourceTendencyReporter = formToolkit.createButton(composite_SourceTendency, "记者",
                SWT.RADIO);
        Button btnRadioButtonSourceTendencyGovern = formToolkit.createButton(composite_SourceTendency, "政府", SWT.RADIO);
        Button btnRadioButtonSourceTendencyInterest = formToolkit.createButton(composite_SourceTendency, "公益团体",
                SWT.RADIO);
        Button btnRadioButtonSourceTendencyExpert = formToolkit.createButton(composite_SourceTendency, "专家学者",
                SWT.RADIO);
        Button btnRadioButtonSourceTendencyFirm = formToolkit.createButton(composite_SourceTendency, "企业", SWT.RADIO);
        Button btnRadioButtonSourceTendencyUnits = formToolkit.createButton(composite_SourceTendency, "事业单位",
                SWT.RADIO);
        Button btnRadioButtonSourceTendencyLeader = formToolkit.createButton(composite_SourceTendency, "政府领导、政协或人大代表",
                SWT.RADIO);
        Button btnRadioButtonSourceTendencyElse = formToolkit.createButton(composite_SourceTendency, "其他", SWT.RADIO);

        sctnSectionSourceTendency.setClient(composite_SourceTendency);

        Section sctnSectionImageTendency = formToolkit.createSection(marksTendencyComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionImageTendency = new FormData();
        fd_sctnSectionImageTendency.top = new FormAttachment(sctnSectionSourceTendency);
        fd_sctnSectionImageTendency.left = new FormAttachment(0);
        sctnSectionImageTendency.setLayoutData(fd_sctnSectionImageTendency);
        formToolkit.paintBordersFor(sctnSectionImageTendency);
        sctnSectionImageTendency.setText("媒体呈现形象");

        Composite composite_ImageTendency = formToolkit.createComposite(sctnSectionImageTendency);
        composite_ImageTendency.setLayout(new GridLayout());
        Button btnRadioButtonImageTendencyPositive = formToolkit.createButton(composite_ImageTendency, "积极健康",
                SWT.RADIO);
        Button btnRadioButtonImageTendencyPoor = formToolkit.createButton(composite_ImageTendency, "可伶悲惨", SWT.RADIO);
        Button btnRadioButtonImageTendencyHappiness = formToolkit.createButton(composite_ImageTendency, "沫恩幸福",
                SWT.RADIO);
        Button btnRadioButtonImageTendencyProblem = formToolkit.createButton(composite_ImageTendency, "问题儿童",
                SWT.RADIO);
        Button btnRadioButtonImageTendencyElse = formToolkit.createButton(composite_ImageTendency, "其他", SWT.RADIO);

        sctnSectionImageTendency.setClient(composite_ImageTendency);

        Section sctnSectionSpecificTendency = formToolkit.createSection(marksTendencyComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionSpecificTendency = new FormData();
        fd_sctnSectionSpecificTendency.top = new FormAttachment(sctnSectionImageTendency);
        fd_sctnSectionSpecificTendency.left = new FormAttachment(0);
        sctnSectionSpecificTendency.setLayoutData(fd_sctnSectionSpecificTendency);
        formToolkit.paintBordersFor(sctnSectionSpecificTendency);
        sctnSectionSpecificTendency.setText("帮助新闻的具体种类");

        Composite composite_SpecificTendency = formToolkit.createComposite(sctnSectionSpecificTendency);
        composite_SpecificTendency.setLayout(new GridLayout());
        Button btnRadioButtonSpecificTendencyDonate = formToolkit.createButton(composite_SpecificTendency, "单纯一次捐款捐物",
                SWT.RADIO);
        Button btnRadioButtonSpecificTendencyTravel = formToolkit.createButton(composite_SpecificTendency,
                "旅游活动安排的项目之一", SWT.RADIO);
        Button btnRadioButtonSpecificTendencyFree = formToolkit.createButton(composite_SpecificTendency, "免费开放",
                SWT.RADIO);
        Button btnRadioButtonSpecificTendencyLong = formToolkit.createButton(composite_SpecificTendency, "设立长期资助项目",
                SWT.RADIO);
        Button btnRadioButtonSpecificTendencyElse = formToolkit.createButton(composite_SpecificTendency, "其他",
                SWT.RADIO);

        sctnSectionSpecificTendency.setClient(composite_SpecificTendency);

        Section sctnSectionSubjectTendency = formToolkit.createSection(marksTendencyComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionSubjectTendency = new FormData();
        fd_sctnSectionSubjectTendency.top = new FormAttachment(sctnSectionSpecificTendency);
        fd_sctnSectionSubjectTendency.left = new FormAttachment(0);
        sctnSectionSubjectTendency.setLayoutData(fd_sctnSectionSubjectTendency);
        formToolkit.paintBordersFor(sctnSectionSubjectTendency);
        sctnSectionSubjectTendency.setText("帮助类新闻的主体");

        Composite composite_SubjectTendency = formToolkit.createComposite(sctnSectionSubjectTendency);
        composite_SubjectTendency.setLayout(new GridLayout());
        Button btnRadioButtonSubjectTendencyGovern = formToolkit.createButton(composite_SubjectTendency, "政府部门",
                SWT.RADIO);
        Button btnRadioButtonSubjectTendencyInterest = formToolkit.createButton(composite_SubjectTendency, "公益团体",
                SWT.RADIO);
        Button btnRadioButtonSubjectTendencyFirm = formToolkit.createButton(composite_SubjectTendency, "企业", SWT.RADIO);
        Button btnRadioButtonSubjectTendencyUnits = formToolkit.createButton(composite_SubjectTendency, "事业单位",
                SWT.RADIO);
        Button btnRadioButtonSubjectTendencyIndividual = formToolkit.createButton(composite_SubjectTendency, "个人",
                SWT.RADIO);

        sctnSectionSubjectTendency.setClient(composite_SubjectTendency);

        Section sctnSectionPraiseTendency = formToolkit.createSection(marksTendencyComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionPraiseTendency = new FormData();
        fd_sctnSectionPraiseTendency.top = new FormAttachment(sctnSectionSubjectTendency, 1);
        fd_sctnSectionPraiseTendency.left = new FormAttachment(0);
        sctnSectionPraiseTendency.setLayoutData(fd_sctnSectionPraiseTendency);
        formToolkit.paintBordersFor(sctnSectionPraiseTendency);
        sctnSectionPraiseTendency.setText("表彰奖励的新闻主体");

        Composite composite_PraiseTendency = formToolkit.createComposite(sctnSectionPraiseTendency);
        composite_PraiseTendency.setLayout(new GridLayout());
        Button btnRadioButtonPraiseTendencyGovern = formToolkit.createButton(composite_PraiseTendency, "政府部门",
                SWT.RADIO);
        Button btnRadioButtonPraiseTendencyInterest = formToolkit.createButton(composite_PraiseTendency, "公益团体",
                SWT.RADIO);
        Button btnRadioButtonPraiseTendencyFirm = formToolkit.createButton(composite_PraiseTendency, "企业", SWT.RADIO);
        Button btnRadioButtonPraiseTendencyUnits = formToolkit.createButton(composite_PraiseTendency, "事业单位",
                SWT.RADIO);
        Button btnRadioButtonPraiseTendencyIndividual = formToolkit.createButton(composite_PraiseTendency, "个人",
                SWT.RADIO);

        sctnSectionPraiseTendency.setClient(composite_PraiseTendency);

        Section sctnSectionReasonTendency = formToolkit.createSection(marksTendencyComposite,
                Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionReasonTendency = new FormData();
        fd_sctnSectionReasonTendency.top = new FormAttachment(sctnSectionPraiseTendency, 1);
        fd_sctnSectionReasonTendency.left = new FormAttachment(0);
        sctnSectionReasonTendency.setLayoutData(fd_sctnSectionReasonTendency);
        formToolkit.paintBordersFor(sctnSectionReasonTendency);
        sctnSectionReasonTendency.setText("农民工子女不能留在城市读书的原因");

        Composite composite_ReasonTendency = formToolkit.createComposite(sctnSectionReasonTendency);
        composite_ReasonTendency.setLayout(new GridLayout());
        Button btnRadioButtonReasonTendencyRegister = formToolkit.createButton(composite_ReasonTendency, "无本地户籍难入公立学校",
                SWT.RADIO);
        Button btnRadioButtonReasonTendencyFee = formToolkit.createButton(composite_ReasonTendency, "私立学校学费高",
                SWT.RADIO);
        Button btnRadioButtonReasonTendencyQuality = formToolkit.createButton(composite_ReasonTendency, "私立学校教学质量没保障",
                SWT.RADIO);
        Button btnRadioButtonReasonTendencyCancel = formToolkit.createButton(composite_ReasonTendency,
                "越来越多的小型私立学校被国家取消办学资格", SWT.RADIO);
        Button btnRadioButtonReasonTendencyElse = formToolkit.createButton(composite_ReasonTendency, "其他", SWT.RADIO);

        sctnSectionReasonTendency.setClient(composite_ReasonTendency);

        // set all formData right align to Theme
        fd_sctnSectionTypeTendency.right = new FormAttachment(sctnSectionThemeTendency, 0, SWT.RIGHT);
        fd_sctnSectionNTypeTendency.right = new FormAttachment(sctnSectionThemeTendency, 0, SWT.RIGHT);
        fd_sctnSectionSourceTendency.right = new FormAttachment(sctnSectionThemeTendency, 0, SWT.RIGHT);
        fd_sctnSectionImageTendency.right = new FormAttachment(sctnSectionThemeTendency, 0, SWT.RIGHT);
        fd_sctnSectionSpecificTendency.right = new FormAttachment(sctnSectionThemeTendency, 0, SWT.RIGHT);
        fd_sctnSectionSubjectTendency.right = new FormAttachment(sctnSectionThemeTendency, 0, SWT.RIGHT);
        fd_sctnSectionPraiseTendency.right = new FormAttachment(sctnSectionThemeTendency, 0, SWT.RIGHT);
        fd_sctnSectionReasonTendency.right = new FormAttachment(sctnSectionThemeTendency, 0, SWT.RIGHT);

        
        ArrayList<Button> btnOrientationMarks = new ArrayList<>();
        btnOrientationMarks.add(btnRadioButtonTypeOrientationCenter);
        btnOrientationMarks.add(btnRadioButtonTypeOrientationProvince);
        btnOrientationMarks.add(btnRadioButtonTypeOrientationMarket);
        btnOrientationMarks.add(btnRadioButtonNTypeOrientationPure);
        btnOrientationMarks.add(btnRadioButtonNTypeOrientationFeature);
        btnOrientationMarks.add(btnRadioButtonNTypeOrientationComment);
        btnOrientationMarks.add(btnRadioButtonNTypeOrientationElse);
        btnOrientationMarks.add(btnRadioButtonThemeOrientationHelp);
        btnOrientationMarks.add(btnRadioButtonThemeOrientationAdvice);
        btnOrientationMarks.add(btnRadioButtonThemeOrientationPraise);
        btnOrientationMarks.add(btnRadioButtonThemeOrientationViolent);
        btnOrientationMarks.add(btnRadioButtonThemeOrientationAbuse);
        btnOrientationMarks.add(btnRadioButtonThemeOrientationCriminal);
        btnOrientationMarks.add(btnRadioButtonThemeOrientationDeath);
        btnOrientationMarks.add(btnRadioButtonThemeOrientationEffort);
        btnOrientationMarks.add(btnRadioButtonThemeOrientationRough);
        btnOrientationMarks.add(btnRadioButtonThemeOrientationElse);
        btnOrientationMarks.add(btnRadioButtonSourceOrientationReporter);
        btnOrientationMarks.add(btnRadioButtonSourceOrientationGovern);
        btnOrientationMarks.add(btnRadioButtonSourceOrientationInterest);
        btnOrientationMarks.add(btnRadioButtonSourceOrientationExpert);
        btnOrientationMarks.add(btnRadioButtonSourceOrientationFirm);
        btnOrientationMarks.add(btnRadioButtonSourceOrientationUnits);
        btnOrientationMarks.add(btnRadioButtonSourceOrientationLeader);
        btnOrientationMarks.add(btnRadioButtonSourceOrientationElse);
        btnOrientationMarks.add(btnRadioButtonImageOrientationPositive);
        btnOrientationMarks.add(btnRadioButtonImageOrientationPoor);
        btnOrientationMarks.add(btnRadioButtonImageOrientationHappiness);
        btnOrientationMarks.add(btnRadioButtonImageOrientationProblem);
        btnOrientationMarks.add(btnRadioButtonImageOrientationElse);
        btnOrientationMarks.add(btnRadioButtonSpecificOrientationDonate);
        btnOrientationMarks.add(btnRadioButtonSpecificOrientationTravel);
        btnOrientationMarks.add(btnRadioButtonSpecificOrientationFree);
        btnOrientationMarks.add(btnRadioButtonSpecificOrientationLong);
        btnOrientationMarks.add(btnRadioButtonSpecificOrientationElse);
        btnOrientationMarks.add(btnRadioButtonSubjectOrientationGovern);
        btnOrientationMarks.add(btnRadioButtonSubjectOrientationInterest);
        btnOrientationMarks.add(btnRadioButtonSubjectOrientationFirm);
        btnOrientationMarks.add(btnRadioButtonSubjectOrientationUnits);
        btnOrientationMarks.add(btnRadioButtonSubjectOrientationIndividual);
        btnOrientationMarks.add(btnRadioButtonPraiseOrientationGovern);
        btnOrientationMarks.add(btnRadioButtonPraiseOrientationInterest);
        btnOrientationMarks.add(btnRadioButtonPraiseOrientationFirm);
        btnOrientationMarks.add(btnRadioButtonPraiseOrientationUnits);
        btnOrientationMarks.add(btnRadioButtonPraiseOrientationIndividual);
        btnOrientationMarks.add(btnRadioButtonReasonOrientationRegister);
        btnOrientationMarks.add(btnRadioButtonReasonOrientationFee);
        btnOrientationMarks.add(btnRadioButtonReasonOrientationQuality);
        btnOrientationMarks.add(btnRadioButtonReasonOrientationCancel);
        btnOrientationMarks.add(btnRadioButtonReasonOrientationElse);

        ArrayList<Button> btnTendencyMarks = new ArrayList<>();
        btnTendencyMarks.add(btnRadioButtonTypeTendencyCenter);
        btnTendencyMarks.add(btnRadioButtonTypeTendencyProvince);
        btnTendencyMarks.add(btnRadioButtonTypeTendencyMarket);
        btnTendencyMarks.add(btnRadioButtonNTypeTendencyPure);
        btnTendencyMarks.add(btnRadioButtonNTypeTendencyFeature);
        btnTendencyMarks.add(btnRadioButtonNTypeTendencyComment);
        btnTendencyMarks.add(btnRadioButtonNTypeTendencyElse);
        btnTendencyMarks.add(btnRadioButtonThemeTendencyHelp);
        btnTendencyMarks.add(btnRadioButtonThemeTendencyAdvice);
        btnTendencyMarks.add(btnRadioButtonThemeTendencyPraise);
        btnTendencyMarks.add(btnRadioButtonThemeTendencyViolent);
        btnTendencyMarks.add(btnRadioButtonThemeTendencyAbuse);
        btnTendencyMarks.add(btnRadioButtonThemeTendencyCriminal);
        btnTendencyMarks.add(btnRadioButtonThemeTendencyDeath);
        btnTendencyMarks.add(btnRadioButtonThemeTendencyEffort);
        btnTendencyMarks.add(btnRadioButtonThemeTendencyRough);
        btnTendencyMarks.add(btnRadioButtonThemeTendencyElse);
        btnTendencyMarks.add(btnRadioButtonSourceTendencyReporter);
        btnTendencyMarks.add(btnRadioButtonSourceTendencyGovern);
        btnTendencyMarks.add(btnRadioButtonSourceTendencyInterest);
        btnTendencyMarks.add(btnRadioButtonSourceTendencyExpert);
        btnTendencyMarks.add(btnRadioButtonSourceTendencyFirm);
        btnTendencyMarks.add(btnRadioButtonSourceTendencyUnits);
        btnTendencyMarks.add(btnRadioButtonSourceTendencyLeader);
        btnTendencyMarks.add(btnRadioButtonSourceTendencyElse);
        btnTendencyMarks.add(btnRadioButtonImageTendencyPositive);
        btnTendencyMarks.add(btnRadioButtonImageTendencyPoor);
        btnTendencyMarks.add(btnRadioButtonImageTendencyHappiness);
        btnTendencyMarks.add(btnRadioButtonImageTendencyProblem);
        btnTendencyMarks.add(btnRadioButtonImageTendencyElse);
        btnTendencyMarks.add(btnRadioButtonSpecificTendencyDonate);
        btnTendencyMarks.add(btnRadioButtonSpecificTendencyTravel);
        btnTendencyMarks.add(btnRadioButtonSpecificTendencyFree);
        btnTendencyMarks.add(btnRadioButtonSpecificTendencyLong);
        btnTendencyMarks.add(btnRadioButtonSpecificTendencyElse);
        btnTendencyMarks.add(btnRadioButtonSubjectTendencyGovern);
        btnTendencyMarks.add(btnRadioButtonSubjectTendencyInterest);
        btnTendencyMarks.add(btnRadioButtonSubjectTendencyFirm);
        btnTendencyMarks.add(btnRadioButtonSubjectTendencyUnits);
        btnTendencyMarks.add(btnRadioButtonSubjectTendencyIndividual);
        btnTendencyMarks.add(btnRadioButtonPraiseTendencyGovern);
        btnTendencyMarks.add(btnRadioButtonPraiseTendencyInterest);
        btnTendencyMarks.add(btnRadioButtonPraiseTendencyFirm);
        btnTendencyMarks.add(btnRadioButtonPraiseTendencyUnits);
        btnTendencyMarks.add(btnRadioButtonPraiseTendencyIndividual);
        btnTendencyMarks.add(btnRadioButtonReasonTendencyRegister);
        btnTendencyMarks.add(btnRadioButtonReasonTendencyFee);
        btnTendencyMarks.add(btnRadioButtonReasonTendencyQuality);
        btnTendencyMarks.add(btnRadioButtonReasonTendencyCancel);
        btnTendencyMarks.add(btnRadioButtonReasonTendencyElse);
        
        ExpandCleanMarksListener cleanMarksOrientationListener = new ExpandCleanMarksListener(btnOrientationMarks);
        sctnSectionTypeOrientation.addExpansionListener(cleanMarksOrientationListener);
        sctnSectionTypeOrientation.addExpansionListener(new ShowOrientationListener(newsmarks, 0, orientationPieChartComposite));
        sctnSectionNTypeOrientation.addExpansionListener(cleanMarksOrientationListener);
        sctnSectionNTypeOrientation.addExpansionListener(new ShowOrientationListener(newsmarks, 1, orientationPieChartComposite));
        sctnSectionThemeOrientation.addExpansionListener(cleanMarksOrientationListener);
        sctnSectionThemeOrientation.addExpansionListener(new ShowOrientationListener(newsmarks, 2, orientationPieChartComposite));
        sctnSectionSourceOrientation.addExpansionListener(cleanMarksOrientationListener);
        sctnSectionSourceOrientation.addExpansionListener(new ShowOrientationListener(newsmarks, 3, orientationPieChartComposite));
        sctnSectionImageOrientation.addExpansionListener(cleanMarksOrientationListener);
        sctnSectionImageOrientation.addExpansionListener(new ShowOrientationListener(newsmarks, 4, orientationPieChartComposite));
        sctnSectionSpecificOrientation.addExpansionListener(cleanMarksOrientationListener);
        sctnSectionSpecificOrientation.addExpansionListener(new ShowOrientationListener(newsmarks, 5, orientationPieChartComposite));
        sctnSectionSubjectOrientation.addExpansionListener(cleanMarksOrientationListener);
        sctnSectionSubjectOrientation.addExpansionListener(new ShowOrientationListener(newsmarks, 6, orientationPieChartComposite));
        sctnSectionPraiseOrientation.addExpansionListener(cleanMarksOrientationListener);
        sctnSectionPraiseOrientation.addExpansionListener(new ShowOrientationListener(newsmarks, 7, orientationPieChartComposite));
        sctnSectionReasonOrientation.addExpansionListener(cleanMarksOrientationListener);
        sctnSectionReasonOrientation.addExpansionListener(new ShowOrientationListener(newsmarks, 8, orientationPieChartComposite));
        
        ExpandCleanMarksListener cleanMarksTendencyListener = new ExpandCleanMarksListener(btnTendencyMarks);
        sctnSectionTypeTendency.addExpansionListener(cleanMarksTendencyListener);
        sctnSectionNTypeTendency.addExpansionListener(cleanMarksTendencyListener);
        sctnSectionThemeTendency.addExpansionListener(cleanMarksTendencyListener);
        sctnSectionSourceTendency.addExpansionListener(cleanMarksTendencyListener);
        sctnSectionImageTendency.addExpansionListener(cleanMarksTendencyListener);
        sctnSectionSpecificTendency.addExpansionListener(cleanMarksTendencyListener);
        sctnSectionPraiseTendency.addExpansionListener(cleanMarksTendencyListener);
        sctnSectionReasonTendency.addExpansionListener(cleanMarksTendencyListener);
        
        ShowSexOrientationListener sexOrientationListener = new ShowSexOrientationListener(sexOrientationCount, orientationPieChartComposite);
        for (Button b: btnOrientationMarks) {
            b.addSelectionListener(sexOrientationListener);
        }
        
        ShowSexTendencyListener sexTendencyListener = new ShowSexTendencyListener(sexTendencyCount, tendencyLineChartComposite);
        for (Button b: btnTendencyMarks) {
            b.addSelectionListener(sexTendencyListener);
        }
        
        return container;
    }

    /**
     * Create the actions.
     */
    private void createActions() {
        // Create the actions
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
