package com.gioov;

import static org.junit.Assert.assertTrue;

import com.gioov.common.tableview.MainWindowTableViewObserver;
import com.gioov.common.Observable;
import com.gioov.common.tableview.TableViewObservable;
import com.gioov.common.tableview.TableViewObserver;
import com.gioov.entity.ProjectEntity;
import com.gioov.util.FileUtil;
import com.gioov.util.RegexUtil;
import com.gioov.util.ResourceUtil;
import com.gioov.util.ZipUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(AppTest.class);

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

//    @Test
//    public void testApp(){
////        System.out.println("img/login_left.png" + " => "+ FileUtil.filterFileSeparator("img/login_left.png"));
////        System.out.println("img\\login_left.png" + " => "+ FileUtil.filterFileSeparator("img\\login_left.png"));
////        System.out.println("/css/default.css" + " => "+ FileUtil.filterFileSeparator("/css/default.css"));
////        System.out.println("\\css\\default.css" + " => "+ FileUtil.filterFileSeparator("\\css\\default.css"));
//
//        String entry = "D:\\test";//需要压缩的文件目录
//        File file = new File(entry);
//        ZipUtil.compress(file,file.getParent() + File.separator+"test",null);
//
//    }

//    @Test
//    public void testUncompress(){
//        File file = new File( "D:\\test.zip");
//        ZipUtil.unCompress(file, file.getParent()+File.separator+"test");
//    }

//    @Test
//    public void testString(){
//
//        File directory = new File( "D:\\backup");
//        String name = "test_20180419_${count}.zip";
//
//        LOGGER.info("forEachIsExist{}",forEachIsExist(directory.listFiles(), name));
//
//    }


//    private int forEachIsExist(File[] files, String name){
//
//        List<Integer> integerList = new ArrayList<>();
////        String name = "test_20180419_${count}.zip";
//        String regex = name.replaceAll("\\$\\{count\\}","([0-9])");
//        Pattern pattern = Pattern.compile(regex);
//        Matcher matcher;
//        for(File file : files) {
//            matcher = pattern.matcher(file.getName());
//            if (matcher.find()) {
//                if (matcher.groupCount() > 0) {
//                    if (matcher.groupCount() > 1) {
//                        String number = matcher.group(matcher.groupCount() - 1);
//                        if (RegexUtil.isNumber(number)) {
//                            integerList.add(Integer.valueOf(number));
//                        }
//                    } else {
//                        String number = matcher.group(1);
//                        if (RegexUtil.isNumber(number)) {
//                            integerList.add(Integer.valueOf(number));
//                        }
//                    }
//                }
//            }
//
//        }
//
//        Collections.reverse(integerList);
//        return integerList.get(0);
//
//    }



//    @Test
//    public void observerTest(){
//
//        Observable<TableViewObserver> tableViewObservable = new TableViewObservable();
//
//        ObservableList<ProjectEntity> projectEntityObservableList = FXCollections.observableArrayList();
//        ProjectEntity projectEntity = new ProjectEntity();
//        projectEntity.setId(1L);
//        projectEntityObservableList.add(projectEntity);
//
//        TableViewObserver  mainWindowTableViewObserver = new MainWindowTableViewObserver();
//
//        tableViewObservable.registerObserver(mainWindowTableViewObserver);
//        ((TableViewObservable) tableViewObservable).setUpdate(projectEntityObservableList);
//        LOGGER.info("{}",((MainWindowTableViewObserver) mainWindowTableViewObserver).getUpdate());
//
//    }

//
//    @Test
//    public void copyTest(){
//        String sourceFilename = "D:\\dev\\IdeaProjects\\servermanager\\src\\main\\resources\\servermanager.db";
//        File sourceFile = new File(sourceFilename);
//        LOGGER.info("sourceFilename={}",sourceFilename);
//        LOGGER.info("sourceFile.exists={}",sourceFile.exists());
//        String targetFilename = "D:\\dev\\IdeaProjects\\servermanager\\servermanager.db";
//        LOGGER.info("targetFilename={}",targetFilename);
//        File targetFile = new File(targetFilename);
//        LOGGER.info("targetFile.exists={}",targetFile.exists());
//
////        if(!targetFile.exists()){
//            FileUtil.copyFile(sourceFile, targetFile);
////        }
//    }


}
