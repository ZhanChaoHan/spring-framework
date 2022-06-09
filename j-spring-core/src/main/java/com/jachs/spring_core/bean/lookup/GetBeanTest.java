package com.jachs.spring_core.bean.lookup;


/****
 * @author  zhanchaohan
 */
public abstract class GetBeanTest {
    public abstract  User getBean();

    public void showMe(){
        this.getBean().showMe();
    }
}
