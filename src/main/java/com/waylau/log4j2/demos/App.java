package com.waylau.log4j2.demos;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App 
{
	private static final Logger logger = LogManager.getLogger(App.class.getName());
    //private static final Logger logger = LogManager.getLogger();
    
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        logger.entry();   //trace级别的信息，单独列出来是希望你在某个方法或者程序逻辑开始的时候调用，和logger.trace("entry")基本一个意思
        while(true){
            logger.error("Did it again!");   //error级别的信息，参数就是你输出的信息
            logger.info("我是info信息");    //info级别的信息
            logger.debug("我是debug信息");
            logger.warn("我是warn信息");
            logger.fatal("我是fatal信息");
            logger.log(Level.DEBUG, "我是debug信息");   //这个就是制定Level类型的调用：谁闲着没事调用这个，也不一定哦！
            logger.exit();    //和entry()对应的结束方法，和logger.trace("exit");一个意思
        }

    }
}
