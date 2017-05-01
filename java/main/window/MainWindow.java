package window;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Group;

import hh.EncodedContent;
import hh.NewsItem;
import hh.NewsList;
import xmlRead.XMLReader;

import org.eclipse.swt.widgets.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;

public class MainWindow extends ApplicationWindow {
    private AnalyzeAction analyzeAction;
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
    public MainWindow() {
        super(null);
        initNewsList();
        createActions();
        addToolBar(SWT.FLAT | SWT.WRAP);
        addMenuBar();
        addStatusLine();
    }

    private void initNewsList() {

        try {
        File file = new File("./res/nanfangdaily.xml");
        XMLReader in = new XMLReader(file);
        ArrayList<Map<String, String>> list = in.readXml();

        int n = list.size();
        ArrayList<NewsItem> li = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            NewsItem newsItem = new NewsItem();
            newsItem.setDate(list.get(i).get("Date"));
            String encodedContent = list.get(i).get("EncodedContent");
            String content = EncodedContent.decode(encodedContent).replaceAll("</?html>|</?body>|</?P>", "");
            newsItem.setContent(content);
            newsItem.setDeleted(Boolean.parseBoolean(list.get(i).get("IsDeleted")));
            newsItem.setLocation(list.get(i).get("Location"));
            li.add(newsItem);
        }

        newsList = new NewsList(li);
        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create contents of the application window.
     * 
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

        final List newsSummaryList = new List(container, SWT.BORDER|SWT.V_SCROLL);
        newsSummaryList.setBounds(0, 0, 478, 613);
        newsSummaryList.setItems(newsList.getNewsSummaryList());

        Group group_AddMarks = new Group(scrolledComposite, SWT.NONE);

        Composite composite_Type = new Composite(group_AddMarks, SWT.NONE);
        composite_Type.setBounds(10, 10, 129, 82);

        text_Type = new Text(composite_Type, SWT.CENTER);
        text_Type.setBounds(0, 0, 73, 20);
        text_Type.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));
        text_Type.setEnabled(true);
        text_Type.setEditable(false);
        text_Type.setText("��ֽ���");

        Button btnRadioButtonTypeCenter = new Button(composite_Type, SWT.RADIO);
        btnRadioButtonTypeCenter.setBounds(0, 25, 97, 17);
        btnRadioButtonTypeCenter.setEnabled(true);
        btnRadioButtonTypeCenter.setText("����һ��");

        Button btnRadioButtonTypeProvince = new Button(composite_Type, SWT.RADIO);
        btnRadioButtonTypeProvince.setBounds(0, 45, 100, 17);
        btnRadioButtonTypeProvince.setEnabled(true);
        btnRadioButtonTypeProvince.setText("ʡһ��");

        Button btnRadioButtonTypeMarket = new Button(composite_Type, SWT.RADIO);
        btnRadioButtonTypeMarket.setBounds(0, 65, 100, 17);
        btnRadioButtonTypeMarket.setEnabled(true);
        btnRadioButtonTypeMarket.setText("��Ӫģʽ�г���");

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
        text_Theme.setText("��������");

        Button btnRadioButtonThemeHelp = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeHelp.setBounds(0, 26, 120, 17);
        btnRadioButtonThemeHelp.setText("����������ذ�");

        Button btnRadioButtonThemeAdvice = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeAdvice.setBounds(0, 46, 250, 17);
        btnRadioButtonThemeAdvice.setText("����������ض�ͯ��������Ľ���Ϳ���");

        Button btnRadioButtonThemePraise = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemePraise.setBounds(0, 66, 250, 17);
        btnRadioButtonThemePraise.setText("���ð����ذ����ض�ͯ�ĵ�λ�����");

        Button btnRadioButtonThemeViolent = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeViolent.setBounds(0, 86, 250, 17);
        btnRadioButtonThemeViolent.setText("���ض�ͯ���ܱ���");

        Button btnRadioButtonThemeAbuse = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeAbuse.setBounds(0, 106, 300, 17);
        btnRadioButtonThemeAbuse.setText("���ض�ͯ�����֡������ǿ����ǻ��С����ӵ�");

        Button btnRadioButtonThemeCriminal = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeCriminal.setBounds(0, 126, 150, 17);
        btnRadioButtonThemeCriminal.setText("���ض�ͯ�����");

        Button btnRadioButtonThemeDeath = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeDeath.setBounds(0, 146, 150, 17);
        btnRadioButtonThemeDeath.setText("���ض�ͯ��������");

        Button btnRadioButtonThemeEffort = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeEffort.setBounds(0, 166, 150, 17);
        btnRadioButtonThemeEffort.setText("���ض�ͯŬ���Ͻ�");

        Button btnRadioButtonThemeRough = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeRough.setBounds(0, 186, 200, 17);
        btnRadioButtonThemeRough.setText("�򹤸�ĸ�ڳ��м�������");

        Button btnRadioButtonThemeElse = new Button(composite_Theme, SWT.RADIO);
        btnRadioButtonThemeElse.setBounds(0, 206, 100, 17);
        btnRadioButtonThemeElse.setText("����");

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
        text_Source.setText("���ű�����Դ");

        Button btnRadioButtonSourceReporter = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceReporter.setBounds(0, 26, 54, 17);
        btnRadioButtonSourceReporter.setText("����");

        Button btnRadioButtonSourceGovern = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceGovern.setBounds(71, 26, 73, 17);
        btnRadioButtonSourceGovern.setText("����");

        Button btnRadioButtonSourceEnte = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceEnte.setBounds(0, 46, 45, 17);
        btnRadioButtonSourceEnte.setText("��ҵ");

        Button btnRadioButtonSourceInstitution = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceInstitution.setBounds(71, 46, 73, 17);
        btnRadioButtonSourceInstitution.setText("��ҵ��λ");

        Button btnRadioButtonSourceTeam = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceTeam.setBounds(0, 66, 69, 17);
        btnRadioButtonSourceTeam.setText("��������");

        Button btnRadioButtonSourceExpert = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceExpert.setBounds(71, 66, 100, 17);
        btnRadioButtonSourceExpert.setText("ר��ѧ��");

        Button btnRadioButtonSourceLeader = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceLeader.setBounds(0, 86, 200, 17);
        btnRadioButtonSourceLeader.setText("�����쵼����Э���˴����");

        Button btnRadioButtonSourceElse = new Button(composite_Source, SWT.RADIO);
        btnRadioButtonSourceElse.setBounds(0, 106, 150, 17);
        btnRadioButtonSourceElse.setText("����");

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
        text_Reason.setText("���ű�����ũ����Ů�������ڳ��ж����ԭ��");

        Button btnRadioButtonReasonRegister = new Button(composite_Reason, SWT.RADIO);
        btnRadioButtonReasonRegister.setBounds(0, 26, 150, 17);
        btnRadioButtonReasonRegister.setText("�ޱ��ػ������빫��ѧУ");

        Button btnRadioButtonReasonFee = new Button(composite_Reason, SWT.RADIO);
        btnRadioButtonReasonFee.setBounds(0, 46, 129, 17);
        btnRadioButtonReasonFee.setText("˽��ѧУѧ�Ѹ�");

        Button btnRadioButtonReasonQuality = new Button(composite_Reason, SWT.RADIO);
        btnRadioButtonReasonQuality.setBounds(0, 66, 173, 17);
        btnRadioButtonReasonQuality.setText("˽��ѧУ��ѧ����û����");

        Button btnRadioButtonReasonCancel = new Button(composite_Reason, SWT.RADIO);
        btnRadioButtonReasonCancel.setBounds(0, 86, 270, 17);
        btnRadioButtonReasonCancel.setText("Խ��Խ���С��˽��ѧУ������ȡ����ѧ�ʸ�");

        Button btnRadioButtonReasonElse = new Button(composite_Reason, SWT.RADIO);
        btnRadioButtonReasonElse.setBounds(0, 106, 80, 17);
        btnRadioButtonReasonElse.setText("����");

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
        text_NType.setText("��������");

        Button btnRadioButtonNTypePure = new Button(composite_NType, SWT.RADIO);
        btnRadioButtonNTypePure.setBounds(0, 25, 100, 17);
        btnRadioButtonNTypePure.setText("��������");

        Button btnRadioButtonNTypeFeature = new Button(composite_NType, SWT.RADIO);
        btnRadioButtonNTypeFeature.setBounds(0, 45, 100, 17);
        btnRadioButtonNTypeFeature.setText("�ظ�����д");

        Button btnRadioButtonNTypeComment = new Button(composite_NType, SWT.RADIO);
        btnRadioButtonNTypeComment.setBounds(0, 65, 97, 17);
        btnRadioButtonNTypeComment.setText("����");

        Button btnRadioButtonNTypeElse = new Button(composite_NType, SWT.RADIO);
        btnRadioButtonNTypeElse.setBounds(0, 85, 97, 17);
        btnRadioButtonNTypeElse.setText("����");

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
        text_Image.setText("ý���������");

        Button btnRadioButtonImagePositive = new Button(composite_Image, SWT.RADIO);
        btnRadioButtonImagePositive.setBounds(0, 26, 97, 17);
        btnRadioButtonImagePositive.setText("��������");

        Button btnRadioButtonImagePoor = new Button(composite_Image, SWT.RADIO);
        btnRadioButtonImagePoor.setBounds(0, 46, 100, 17);
        btnRadioButtonImagePoor.setText("���汯��");

        Button btnRadioButtonImageHappiness = new Button(composite_Image, SWT.RADIO);
        btnRadioButtonImageHappiness.setBounds(0, 66, 100, 17);
        btnRadioButtonImageHappiness.setText("ĭ���Ҹ�");

        Button btnRadioButtonImageProblem = new Button(composite_Image, SWT.RADIO);
        btnRadioButtonImageProblem.setBounds(0, 86, 97, 17);
        btnRadioButtonImageProblem.setText("�����ͯ");

        Button btnRadioButtonImageElse = new Button(composite_Image, SWT.RADIO);
        btnRadioButtonImageElse.setBounds(0, 106, 97, 17);
        btnRadioButtonImageElse.setText("����");

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
        text_Subject.setText("���������ŵ�����");

        Button btnRadioButtonSubjectGovern = new Button(composite_Subject, SWT.RADIO);
        btnRadioButtonSubjectGovern.setBounds(0, 26, 100, 17);
        btnRadioButtonSubjectGovern.setText("��������");

        Button btnRadioButtonSubjectFirm = new Button(composite_Subject, SWT.RADIO);
        btnRadioButtonSubjectFirm.setBounds(0, 46, 100, 17);
        btnRadioButtonSubjectFirm.setText("��ҵ");

        Button btnRadioButtonSubjectUnits = new Button(composite_Subject, SWT.RADIO);
        btnRadioButtonSubjectUnits.setBounds(0, 66, 97, 17);
        btnRadioButtonSubjectUnits.setText("��ҵ��λ");

        Button btnRadioButtonSubjectInterest = new Button(composite_Subject, SWT.RADIO);
        btnRadioButtonSubjectInterest.setBounds(0, 86, 97, 17);
        btnRadioButtonSubjectInterest.setText("��������");

        Button btnRadioButtonSubjectIndividual = new Button(composite_Subject, SWT.RADIO);
        btnRadioButtonSubjectIndividual.setBounds(0, 106, 97, 17);
        btnRadioButtonSubjectIndividual.setText("����");

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
        text_Specific.setText("�������ŵľ�������");

        Button btnRadioButtonSpecificDonate = new Button(composite_Specific, SWT.RADIO);
        btnRadioButtonSpecificDonate.setBounds(0, 26, 100, 17);
        btnRadioButtonSpecificDonate.setText("����һ�ξ�����");

        Button btnRadioButtonSpecificTravel = new Button(composite_Specific, SWT.RADIO);
        btnRadioButtonSpecificTravel.setBounds(0, 46, 100, 17);
        btnRadioButtonSpecificTravel.setText("���λ���ŵ���Ŀ֮һ");

        Button btnRadioButtonSpecificFree = new Button(composite_Specific, SWT.RADIO);
        btnRadioButtonSpecificFree.setBounds(0, 66, 97, 17);
        btnRadioButtonSpecificFree.setText("��ѿ���");

        Button btnRadioButtonSpecificLong = new Button(composite_Specific, SWT.RADIO);
        btnRadioButtonSpecificLong.setBounds(0, 86, 97, 17);
        btnRadioButtonSpecificLong.setText("��������������Ŀ");

        Button btnRadioButtonSpecificElse = new Button(composite_Specific, SWT.RADIO);
        btnRadioButtonSpecificElse.setBounds(0, 106, 97, 17);
        btnRadioButtonSpecificElse.setText("����");

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
        text_Praise.setText("���ý�������������");

        Button btnRadioButtonPraiseGovern = new Button(composite_Praise, SWT.RADIO);
        btnRadioButtonPraiseGovern.setBounds(0, 26, 100, 17);
        btnRadioButtonPraiseGovern.setText("��������");

        Button btnRadioButtonPraiseFirm = new Button(composite_Praise, SWT.RADIO);
        btnRadioButtonPraiseFirm.setBounds(0, 46, 100, 17);
        btnRadioButtonPraiseFirm.setText("��ҵ");

        Button btnRadioButtonPraiseUnits = new Button(composite_Praise, SWT.RADIO);
        btnRadioButtonPraiseUnits.setBounds(0, 66, 97, 17);
        btnRadioButtonPraiseUnits.setText("��ҵ��λ");

        Button btnRadioButtonPraiseInterest = new Button(composite_Praise, SWT.RADIO);
        btnRadioButtonPraiseInterest.setBounds(0, 86, 97, 17);
        btnRadioButtonPraiseInterest.setText("��������");

        Button btnRadioButtonPraiseIndividual = new Button(composite_Praise, SWT.RADIO);
        btnRadioButtonPraiseIndividual.setBounds(0, 106, 97, 17);
        btnRadioButtonPraiseIndividual.setText("����");

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
        text_Sex.setText("�Ա�");
        text_Sex.setEnabled(true);
        text_Sex.setEditable(false);
        text_Sex.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND));

        Button btnRadioButtonSexMan = new Button(composite_Sex, SWT.RADIO);
        btnRadioButtonSexMan.setBounds(0, 20, 106, 17);
        btnRadioButtonSexMan.setText("��");

        Button btnRadioButtonSexWoman = new Button(composite_Sex, SWT.RADIO);
        btnRadioButtonSexWoman.setBounds(0, 40, 106, 17);
        btnRadioButtonSexWoman.setText("Ů");

        ArrayList<Button> btnSex = new ArrayList<>();
        btnSex.add(btnRadioButtonSexMan);
        btnSex.add(btnRadioButtonSexWoman);

        ArrayList<ArrayList<Button>> btnMarks = new ArrayList<>();
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
        btnNewButton.addSelectionListener(new SelectionAdapter() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                for (ArrayList<Button> markGroupType : btnMarks) {
                    for (Button btn : markGroupType) {
                        if (btn.getSelection()) {
                            System.out.println("select is: " + btn.getText());
                            btn.setSelection(false);
                            markGroupType.get(0).setSelection(true);
                        }
                    }
                }
                int i = newsSummaryList.getFocusIndex();
                newsSummaryList.setSelection(i + 1);
            }
        });
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

        // Create the actions
        analyzeAction = new AnalyzeAction("Analysis");

    }

    /**
     * Create the menu manager.
     * 
     * @return the menu manager
     */
    @Override
    protected MenuManager createMenuManager() {
        MenuManager menuManager = new MenuManager("");
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
        toolBarManager.add(analyzeAction);
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
            MainWindow window = new MainWindow();
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
        newShell.setText("MainWindow");
    }

    /**
     * Return the initial size of the window.
     */
    @Override
    protected Point getInitialSize() {
        return new Point(1009, 734);
    }
}
