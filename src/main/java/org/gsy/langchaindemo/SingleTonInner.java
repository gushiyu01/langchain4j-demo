package org.gsy.langchaindemo;

/**
 * @program: langchain4j-demo
 * @description:
 * @author: GSY
 * @create: 2025-07-29 09:16
 **/
public class SingleTonInner {

    /**
     * 私有构造方法，外部无法实例化
     */
    private SingleTonInner(){}

    private static class SingleTonHolder{
        private static final SingleTonInner INS = new SingleTonInner();
    }

    public static SingleTonInner getInstance(){
        return SingleTonHolder.INS;
    }
}
