package com.coderbuff.third2resttemplateprop.study.mydesign;




/**
 * @Author 喻可
 * @Date 2021/12/24 09:42
 */
public abstract class CmdbItemOperationAbstract {

    /**
     * 获取操作类型
     */
    public abstract OperateType getOperationType();


    public abstract void doAction();


    //模板方法的使用
    public void templateMethod() {

        System.out.println("模板方法开始");

        doAction();

        System.out.println("模板方法结束");
    }


    /**
     * 抽象类里不能注入其他对象,通过SpringContextUtil获取bean对象
     *
     * @param sn
     */
//    public CmdbMachineDTO queryDeviceFromCmdbBySn(String sn) {
//        CdnQueryMachineByCondition machineByCondition = SpringContextUtil.getBean("cdnQueryMachineByCondition", CdnQueryMachineByCondition.class);
//        List<CmdbMachineDTO> deviceBySn = machineByCondition.getDeviceBySn(sn);
//        if (!ValidateUtils.isEmpty(deviceBySn)) {
//            CmdbMachineDTO cmdbMachineDTO = deviceBySn.get(0);
//            return cmdbMachineDTO;
//        }
//        return null;
//    }


}
