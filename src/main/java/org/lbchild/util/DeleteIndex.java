package org.lbchild.util;

import java.util.ArrayList;
import java.util.List;


public class DeleteIndex {
    
    private static List<Integer> deleteIndex = null;

    public static List<Integer> getInstance() {
        if (deleteIndex == null)
            deleteIndex = new ArrayList<>();
        
        return deleteIndex;
    }

//    private static List<Integer> readDeleteIndex() {
//        // File file = new File("src/main/resources/delete-index.txt");
//        List<Integer> ret = new ArrayList<>();
//        try {
//            
//            
////            BufferedReader in = new BufferedReader(new FileReader(file));
////            String lineStr = in.readLine();
////
////            if (lineStr != null) {
////                String[] deleteIndexStr = lineStr.split(" ");
////                for (String s : deleteIndexStr) {
////                    ret.add(Integer.valueOf(s));
////                }
////            }
////            in.close();
//
////            logger.info("finishing reading delte-index.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return ret;
//    }

//    public static void writeFile() {
//        try {
//            FileWriter out = new FileWriter(new File("src/main/resources/delete-index.txt"));
//            for (int i = 0; i < deleteIndex.size(); i++) {
//                out.write(deleteIndex.get(i) + " ");
//            }
//            out.close();
//
//            logger.info("finishing writing delte-index.txt");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
