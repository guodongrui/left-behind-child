package org.lbchild.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.lbchild.model.User;
import org.lbchild.xml.XMLReader;

public class CountLabel {
    public final String NEWSMARKS = "NewsMarks";
    public final String DATE = "Date";
    private List<Map<String, String>> orgLabelList;

    private ArrayList<String> labelList = new ArrayList<>();

    public CountLabel(List<Map<String, String>> orgLabelList) {
        this.orgLabelList = orgLabelList;
    }

    public double[][][][] getLabelDateCount() {
        String[][] allMarks = new String[orgLabelList.size()][11]; // 全部新闻的标签，第11位存放年份

        int news_i = 0;
        for (Map<String, String> map : orgLabelList) {
            if (!map.containsKey(NEWSMARKS)) {
                continue;
            }
            labelToList(Crypt.decryptContent(map.get(NEWSMARKS), User.getInstance().getUserName()));

            for (int j = 0; j < allMarks[news_i].length - 1; ++j)
                allMarks[news_i][j] = labelList.get(j);

            allMarks[news_i][allMarks[news_i].length - 1] = map.get(DATE);

            labelList.clear();
            ++news_i;

        }

        double[][][][] dateCount = new double[9][][][];

        dateCount[0] = new double[3][2][10];
        dateCount[1] = new double[4][2][10];
        dateCount[2] = new double[10][2][10];
        dateCount[3] = new double[8][2][10];
        dateCount[4] = new double[5][2][10];
        dateCount[5] = new double[5][2][10];
        dateCount[6] = new double[5][2][10];
        dateCount[7] = new double[5][2][10];
        dateCount[8] = new double[5][2][10];

        for (int i = 0; i < dateCount.length; ++i)
            for (int j = 0; j < dateCount[i].length; ++j)
                for (int k = 0; k < dateCount[i][j].length; ++k)
                    for (int m = 0; m < dateCount[i][j][k].length; ++m)
                        dateCount[i][j][k][m] = 0;

        for (int i = 0; i < allMarks.length; ++i) {
            for (int j = 0; j < 9; ++j) { // 下标9存放性别，不需要计入标签种类统计
                String s = allMarks[i][j];
                String sex = allMarks[i][9];
                int date = Integer.parseInt(allMarks[i][10]);
                ++dateCount[j][countLabelPos(j, s)][countLabelPos(9, sex)][date - 2006];
            }
        }

        return dateCount;
    }

    public int[][][] getLabelSexCount() {
        String[][] allMarks = new String[orgLabelList.size()][10];

        int news_i = 0;
        for (Map<String, String> map : orgLabelList) {
            if (!map.containsKey(NEWSMARKS)) {
                continue;
            }
            labelToList(Crypt.decryptContent(map.get(NEWSMARKS), User.getInstance().getUserName()));

            for (int j = 0; j < allMarks[news_i].length; ++j)
                allMarks[news_i][j] = labelList.get(j);

            labelList.clear();
            ++news_i;

        }
        int[][][] marksCategoryStatistic = new int[9][][];
        marksCategoryStatistic[0] = new int[3][2];
        marksCategoryStatistic[1] = new int[4][2];
        marksCategoryStatistic[2] = new int[10][2];
        marksCategoryStatistic[3] = new int[8][2];
        marksCategoryStatistic[4] = new int[5][2];
        marksCategoryStatistic[5] = new int[5][2];
        marksCategoryStatistic[6] = new int[5][2];
        marksCategoryStatistic[7] = new int[5][2];
        marksCategoryStatistic[8] = new int[5][2];

        for (int i = 0; i < marksCategoryStatistic.length; ++i) {
            for (int j = 0; j < marksCategoryStatistic[i].length; ++j) {
                for (int k = 0; k < marksCategoryStatistic[i][j].length; ++k) {
                    marksCategoryStatistic[i][j][k] = 0;
                }
            }
        }

        for (int i = 0; i < allMarks.length; ++i) {
            for (int j = 0; j < 9; ++j) { // 下标9存放性别，不需要计入标签种类统计
                String s = allMarks[i][j];
                String sex = allMarks[i][9];
                ++marksCategoryStatistic[j][countLabelPos(j, s)][countLabelPos(9, sex)];
            }
        }

        return marksCategoryStatistic;

    }

    public static int countTitlePos(String title) {
        if (title.equals("报纸类别")) {
            return 0;
        } else if (title.equals("新闻类型")) {
            return 1;
        } else if (title.equals("报道主题")) {
            return 2;
        } else if (title.equals("新闻报道来源")) {
            return 3;
        } else if (title.equals("媒体呈现形象")) {
            return 4;
        } else if (title.equals("帮助新闻的具体种类")) {
            return 5;
        } else if (title.equals("帮助类新闻的主体")) {
            return 6;
        } else if (title.equals("表彰奖励的新闻主体")) {
            return 7;
        } else if (title.equals("农民工子女不能留在城市读书的原因")) {
            return 8;
        } else if (title.equals("性别")) {
            return 9;
        }
        return -1;
    }

    public static int countLabelPos(int i, String s) {
        if (i == 0) {
            if (s.equals("中央一级")) {
                return 0;
            } else if (s.equals("省一级")) {
                return 1;
            } else if (s.equals("经营模式市场化")) {
                return 2;
            } else {
                return -1;
            }
        } else if (i == 1) {
            if (s.equals("纯净新闻")) {
                return 0;
            } else if (s.equals("特稿与特写")) {
                return 1;
            } else if (s.equals("评论")) {
                return 2;
            } else if (s.equals("其他")) {
                return 3;
            } else {
                return -1;
            }
        } else if (i == 2) {

            if (s.equals("社会各界帮助关爱")) {
                return 0;
            } else if (s.equals("社会各界对留守儿童现象提出的建议和看法")) {
                return 1;
            } else if (s.equals("表彰帮助关爱留守儿童的单位或个人")) {
                return 2;
            } else if (s.equals("留守儿童遭受暴力")) {
                return 3;
            } else if (s.equals("留守儿童被性侵、猥亵、强奸或是怀孕、生子等")) {
                return 4;
            } else if (s.equals("留守儿童犯罪等")) {
                return 5;
            } else if (s.equals("留守儿童意外死亡")) {
                return 6;
            } else if (s.equals("留守儿童努力上进")) {
                return 7;
            } else if (s.equals("打工父母在城市艰难生活")) {
                return 8;
            } else if (s.equals("其他")) {
                return 9;
            } else {
                return -1;
            }
        } else if (i == 3) {
            if (s.equals("记者")) {
                return 0;
            } else if (s.equals("政府")) {
                return 1;
            } else if (s.equals("公益团体")) {
                return 2;
            } else if (s.equals("专家学者")) {
                return 3;
            } else if (s.equals("企业")) {
                return 4;
            } else if (s.equals("事业单位")) {
                return 5;
            } else if (s.equals("政府领导、政协或人大代表")) {
                return 6;
            } else if (s.equals("其他")) {
                return 7;
            } else {
                return -1;
            }
        } else if (i == 4) {
            if (s.equals("积极健康")) {
                return 0;
            } else if (s.equals("可伶悲惨")) {
                return 1;
            } else if (s.equals("沫恩幸福")) {
                return 2;
            } else if (s.equals("问题儿童")) {
                return 3;
            } else if (s.equals("其他")) {
                return 4;
            } else {
                return -1;
            }
        } else if (i == 5) {
            if (s.equals("单纯一次捐款捐物")) {
                return 0;
            } else if (s.equals("旅游活动安排的项目之一")) {
                return 1;
            } else if (s.equals("免费开放")) {
                return 2;
            } else if (s.equals("设立长期资助项目")) {
                return 3;
            } else if (s.equals("其他")) {
                return 4;
            } else {
                return -1;
            }
        } else if (i == 6) {
            if (s.equals("政府部门")) {
                return 0;
            } else if (s.equals("公益团体")) {
                return 1;
            } else if (s.equals("企业")) {
                return 2;
            } else if (s.equals("事业单位")) {
                return 3;
            } else if (s.equals("个人")) {
                return 4;
            } else {
                return -1;
            }
        } else if (i == 7) {
            if (s.equals("政府部门")) {
                return 0;
            } else if (s.equals("公益团体")) {
                return 1;
            } else if (s.equals("企业")) {
                return 2;
            } else if (s.equals("事业单位")) {
                return 3;
            } else if (s.equals("个人")) {
                return 4;
            } else {
                return -1;
            }
        } else if (i == 8) {
            if (s.equals("无本地户籍难入公立学校")) {
                return 0;
            } else if (s.equals("私立学校学费高")) {
                return 1;
            } else if (s.equals("私立学校教学质量没保障")) {
                return 2;
            } else if (s.equals("越来越多的小型私立学校被国家取消办学资格")) {
                return 3;
            } else if (s.equals("其他")) {
                return 4;
            } else {
                return -1;
            }
        } else if (i == 9) {
            if (s.equals("男")) {
                return 0;
            } else if (s.equals("女")) {
                return 1;
            }
        }

        return -1;
    }

    public ArrayList<Map<String, Integer>> getLabelCount() {
        ArrayList<Map<String, Integer>> countList = new ArrayList<>();
        Map<String, Integer> countMap;

        for (Map<String, String> map : orgLabelList) {
            if (!map.containsKey(NEWSMARKS)) {
                continue;
            }
            labelToList(Crypt.decryptContent(map.get(NEWSMARKS), User.getInstance().getUserName()));

            for (int i = 0; i < labelList.size(); i++) {
                String label = labelList.get(i);
                if (countList.size() < i + 1) {
                    Map<String, Integer> newMap = new HashMap<>();
                    newMap.put(label, 1);
                    countList.add(i, newMap);
                } else {
                    countMap = countList.get(i);
                    if (!countMap.containsKey(label)) {
                        countMap.put(label, 1);
                    } else {
                        countMap.put(label, countMap.get(label) + 1);
                    }
                }
            }

            labelList.clear();
        }
        return countList;
    }

    private void labelToList(String label) {
        if (label.length() < 1) {
            return;
        }

        int preIndex = 0;
        int currentIndex = 0;
        while ((currentIndex = label.indexOf("|", preIndex)) != -1) {

            labelList.add(label.substring(preIndex, currentIndex));
            preIndex = currentIndex + 1;
        }
        labelList.add(label.substring(preIndex));

    }

    public static void main(String[] args) {
        File file = new File("src/main/resources/guodongrui/newsmarks.xml");
        XMLReader in = new XMLReader(file);
        ArrayList<Map<String, String>> arr = in.readXml();

        CountLabel cl = new CountLabel(arr);

        ArrayList<Map<String, Integer>> newsmarks = cl.getLabelCount();

        for (Map<String, Integer> test : newsmarks) {
            for (Entry<String, Integer> entry : test.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            System.out.println();
        }

        int[][][] sexCount = cl.getLabelSexCount();

        for (int i = 0; i < sexCount.length; ++i) {
            for (int j = 0; j < sexCount[i].length; ++j) {
                for (int k = 0; k < sexCount[i][j].length; ++k)
                    System.out.println(i + " " + j + " " + k + " :" + sexCount[i][j][k]);
                System.out.println();
            }
        }

        double[][][][] dateCount = cl.getLabelDateCount();
        for (int i = 0; i < dateCount.length; ++i) {
            for (int j = 0; j < dateCount[i].length; ++j) {
                for (int k = 0; k < dateCount[i][j].length; ++k) {
                    for (int m = 0; m < dateCount[i][j][k].length; ++m)
                        System.out.print(dateCount[i][j][k][m] + "  ");
                    System.out.println();
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
