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
        sctnSectionType.setText("��ֽ���");
        
        
        Composite composite_Type = formToolkit.createComposite(sctnSectionType);
        composite_Type.setLayout(new GridLayout());
        Button btnRadioButtonTypeCenter = formToolkit.createButton(composite_Type, "����һ��", SWT.RADIO);
        Button btnRadioButtonTypeProvince = formToolkit.createButton(composite_Type, "ʡһ��", SWT.RADIO);
        Button btnRadioButtonTypeMarket = formToolkit.createButton(composite_Type, "��Ӫģʽ�г���", SWT.RADIO);

        sctnSectionType.setClient(composite_Type);
        
        //-------------NType
        Section sctnSecionNType = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        fd_sctnSectionType.right = new FormAttachment(sctnSecionNType, 0, SWT.RIGHT);
        FormData fd_sctnSecionNType = new FormData();
        fd_sctnSecionNType.top = new FormAttachment(sctnSectionType);
        fd_sctnSecionNType.left = new FormAttachment(0);
        sctnSecionNType.setLayoutData(fd_sctnSecionNType);
        formToolkit.paintBordersFor(sctnSecionNType);
        sctnSecionNType.setText("��������");
        
        Composite composite_NType = formToolkit.createComposite(sctnSecionNType);
        composite_NType.setLayout(new GridLayout());
        Button btnRadioButtonNTypePure = formToolkit.createButton(composite_NType, "��������", SWT.RADIO);
        Button btnRadioButtonNTypeFeature = formToolkit.createButton(composite_NType, "�ظ�����д", SWT.RADIO);
        Button btnRadioButtonNTypeComment = formToolkit.createButton(composite_NType, "����", SWT.RADIO);
        Button btnRadioButtonNTypeElse = formToolkit.createButton(composite_NType, "����", SWT.RADIO);
        
        sctnSecionNType.setClient(composite_NType);
        
        // -----
        Section sctnSectionTheme = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionTheme = new FormData();
        fd_sctnSectionTheme.top = new FormAttachment(sctnSecionNType);
        fd_sctnSectionTheme.left = new FormAttachment(0);
        sctnSectionTheme.setLayoutData(fd_sctnSectionTheme);
        formToolkit.paintBordersFor(sctnSectionTheme);
        sctnSectionTheme.setText("��������");
        
        Composite composite_Theme = formToolkit.createComposite(sctnSectionTheme);
        composite_Theme.setLayout(new GridLayout());
        Button btnRadioButtonThemeHelp = formToolkit.createButton(composite_Theme, "����������ذ�", SWT.RADIO);
        Button btnRadioButtonThemeAdvice = formToolkit.createButton(composite_Theme, "����������ض�ͯ��������Ľ���Ϳ���", SWT.RADIO);
        Button btnRadioButtonThemePraise = formToolkit.createButton(composite_Theme, "���ð����ذ����ض�ͯ�ĵ�λ�����", SWT.RADIO);
        Button btnRadioButtonThemeViolent = formToolkit.createButton(composite_Theme, "���ض�ͯ���ܱ���", SWT.RADIO);
        Button btnRadioButtonThemeAbuse = formToolkit.createButton(composite_Theme, "���ض�ͯ�����֡������ǿ����ǻ��С����ӵ�", SWT.RADIO);
        Button btnRadioButtonThemeCriminal = formToolkit.createButton(composite_Theme, "���ض�ͯ�����", SWT.RADIO);
        Button btnRadioButtonThemeDeath = formToolkit.createButton(composite_Theme, "���ض�ͯ��������", SWT.RADIO);
        Button btnRadioButtonThemeEffort = formToolkit.createButton(composite_Theme, "���ض�ͯŬ���Ͻ�", SWT.RADIO);
        Button btnRadioButtonThemeRough = formToolkit.createButton(composite_Theme, "�򹤸�ĸ�ڳ��м�������", SWT.RADIO);
        Button btnRadioButtonThemeElse = formToolkit.createButton(composite_Theme, "����", SWT.RADIO);
        
        sctnSectionTheme.setClient(composite_Theme);
        
        //-------
        Section sctnSectionSource = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionSource = new FormData();
        fd_sctnSectionSource.left = new FormAttachment(0);
        fd_sctnSectionSource.top = new FormAttachment(sctnSectionTheme, 1);
        sctnSectionSource.setLayoutData(fd_sctnSectionSource);
        formToolkit.paintBordersFor(sctnSectionSource);
        sctnSectionSource.setText("���ű�����Դ");
        sctnSectionSource.setExpanded(true);
        
        Composite composite_Source = formToolkit.createComposite(sctnSectionSource);
        composite_Source.setLayout(new GridLayout());
        Button btnRadioButtonSourceReporter = formToolkit.createButton(composite_Source, "����", SWT.RADIO);
        Button btnRadioButtonSourceGovern = formToolkit.createButton(composite_Source, "����", SWT.RADIO);
        Button btnRadioButtonSourceInterest = formToolkit.createButton(composite_Source, "��������", SWT.RADIO);
        Button btnRadioButtonSourceExpert = formToolkit.createButton(composite_Source, "ר��ѧ��", SWT.RADIO);
        Button btnRadioButtonSourceFirm = formToolkit.createButton(composite_Source, "��ҵ", SWT.RADIO);
        Button btnRadioButtonSourceUnits = formToolkit.createButton(composite_Source, "��ҵ��λ", SWT.RADIO);
        Button btnRadioButtonSourceLeader = formToolkit.createButton(composite_Source, "�����쵼����Э���˴����", SWT.RADIO);
        Button btnRadioButtonSourceElse = formToolkit.createButton(composite_Source, "����", SWT.RADIO);
        
        sctnSectionSource.setClient(composite_Source);
        
        //--------
        Section sctnSectionImage = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionImage = new FormData();
        fd_sctnSectionImage.right = new FormAttachment(sctnSectionType, 0, SWT.RIGHT);
        fd_sctnSectionImage.left = new FormAttachment(0);
        fd_sctnSectionImage.top = new FormAttachment(sctnSectionSource);
        sctnSectionImage.setLayoutData(fd_sctnSectionImage);
        formToolkit.paintBordersFor(sctnSectionImage);
        sctnSectionImage.setText("ý���������");
        
        Composite composite_Image = formToolkit.createComposite(sctnSectionImage);
        composite_Image.setLayout(new GridLayout());
        Button btnRadioButtonImagePositive = formToolkit.createButton(composite_Image, "��������", SWT.RADIO);
        Button btnRadioButtonImagePoor = formToolkit.createButton(composite_Image, "���汯��", SWT.RADIO);
        Button btnRadioButtonImageHappiness = formToolkit.createButton(composite_Image, "ĭ���Ҹ�", SWT.RADIO);
        Button btnRadioButtonImageProblem = formToolkit.createButton(composite_Image, "�����ͯ", SWT.RADIO);
        Button btnRadioButtonImageElse = formToolkit.createButton(composite_Image, "����", SWT.RADIO);
        
        sctnSectionImage.setClient(composite_Image);
        
        //-----
        Section sctnSectionSpecific = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionSpecific = new FormData();
        fd_sctnSectionSpecific.top = new FormAttachment(sctnSectionImage);
        fd_sctnSectionSpecific.right = new FormAttachment(sctnSectionType, 0, SWT.RIGHT);
        fd_sctnSectionSpecific.left = new FormAttachment(0);
        sctnSectionSpecific.setLayoutData(fd_sctnSectionSpecific);
        formToolkit.paintBordersFor(sctnSectionSpecific);
        sctnSectionSpecific.setText("�������ŵľ�������");
        
        Composite composite_Specific = formToolkit.createComposite(sctnSectionSpecific);
        composite_Specific.setLayout(new GridLayout());
        Button btnRadioButtonSpecificDonate = formToolkit.createButton(composite_Specific, "����һ�ξ�����", SWT.RADIO);
        Button btnRadioButtonSpecificTravel = formToolkit.createButton(composite_Specific, "���λ���ŵ���Ŀ֮һ", SWT.RADIO);
        Button btnRadioButtonSpecificFree = formToolkit.createButton(composite_Specific, "��ѿ���", SWT.RADIO);
        Button btnRadioButtonSpecificLong = formToolkit.createButton(composite_Specific, "��������������Ŀ", SWT.RADIO);
        Button btnRadioButtonSpecificElse = formToolkit.createButton(composite_Specific, "����", SWT.RADIO);
        
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
        sctnSectionSubject.setText("���������ŵ�����");
        
        Composite composite_Subject = formToolkit.createComposite(sctnSectionSubject);
        composite_Subject.setLayout(new GridLayout());
        Button btnRadioButtonSubjectGovern = formToolkit.createButton(composite_Subject, "��������", SWT.RADIO);
        Button btnRadioButtonSubjectInterest = formToolkit.createButton(composite_Subject, "��������", SWT.RADIO);
        Button btnRadioButtonSubjectFirm = formToolkit.createButton(composite_Subject, "��ҵ", SWT.RADIO);
        Button btnRadioButtonSubjectUnits = formToolkit.createButton(composite_Subject, "��ҵ��λ", SWT.RADIO);
        Button btnRadioButtonSubjectIndividual = formToolkit.createButton(composite_Subject, "����", SWT.RADIO);
 
        sctnSectionSubject.setClient(composite_Subject);  
        
        //---------
        Section sctnSectionPraise = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        FormData fd_sctnSectionPraise = new FormData();
        fd_sctnSectionPraise.right = new FormAttachment(sctnSectionType, 0, SWT.RIGHT);
        fd_sctnSectionPraise.top = new FormAttachment(sctnSectionSubject, 1);
        fd_sctnSectionPraise.left = new FormAttachment(0);
        sctnSectionPraise.setLayoutData(fd_sctnSectionPraise);
        formToolkit.paintBordersFor(sctnSectionPraise);
        sctnSectionPraise.setText("���ý�������������");
        
        Composite composite_Praise = formToolkit.createComposite(sctnSectionPraise);
        composite_Praise.setLayout(new GridLayout());
        Button btnRadioButtonPraiseGovern = formToolkit.createButton(composite_Praise, "��������", SWT.RADIO);
        Button btnRadioButtonPraiseInterest = formToolkit.createButton(composite_Praise, "��������", SWT.RADIO);
        Button btnRadioButtonPraiseFirm = formToolkit.createButton(composite_Praise, "��ҵ", SWT.RADIO);
        Button btnRadioButtonPraiseUnits = formToolkit.createButton(composite_Praise, "��ҵ��λ", SWT.RADIO);
        Button btnRadioButtonPraiseIndividual = formToolkit.createButton(composite_Praise, "����", SWT.RADIO);
        
        sctnSectionPraise.setClient(composite_Praise); 
    
        //------------
        Section sctnSectionReason = formToolkit.createSection(container, Section.TWISTIE | Section.TITLE_BAR);
        fd_sctnSectionTheme.right = new FormAttachment(sctnSectionReason, 0, SWT.RIGHT);
        FormData fd_sctnSectionReason = new FormData();
        fd_sctnSectionReason.top = new FormAttachment(sctnSectionPraise, 1);
        fd_sctnSectionReason.left = new FormAttachment(0);
        sctnSectionReason.setLayoutData(fd_sctnSectionReason);
        formToolkit.paintBordersFor(sctnSectionReason);
        sctnSectionReason.setText("ũ����Ů�������ڳ��ж����ԭ��");
        
        Composite composite_Reason = formToolkit.createComposite(sctnSectionReason);
        composite_Reason.setLayout(new GridLayout());
        Button btnRadioButtonReasonRegister = formToolkit.createButton(composite_Reason, "�ޱ��ػ������빫��ѧУ", SWT.RADIO);
        Button btnRadioButtonReasonFee = formToolkit.createButton(composite_Reason, "˽��ѧУѧ�Ѹ�", SWT.RADIO);
        Button btnRadioButtonReasonQuality = formToolkit.createButton(composite_Reason, "˽��ѧУ��ѧ����û����", SWT.RADIO);
        Button btnRadioButtonReasonCancel = formToolkit.createButton(composite_Reason, "Խ��Խ���С��˽��ѧУ������ȡ����ѧ�ʸ�", SWT.RADIO);
        Button btnRadioButtonReasonElse = formToolkit.createButton(composite_Reason, "����", SWT.RADIO);
        
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
        map.put("��", 7);
        map.put("Ů", 3);
        PieChart pieChart = new PieChart(map);
        pieChart.setTile("���Թ���");
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
