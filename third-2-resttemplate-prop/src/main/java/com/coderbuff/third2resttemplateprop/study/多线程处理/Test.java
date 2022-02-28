//package com.coderbuff.third2resttemplateprop.study.多线程处理;
//
//import org.apache.commons.collections4.ListUtils;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.CopyOnWriteArrayList;
//
///**
// * @title: Test
// * @Author yuke
// * @Date: 2022/2/28 22:40
// */
//public class Test {
//    public void test() {
//        List<CdnMachineTemplateDTO> dtoList = cdnResourceQueryService.queryAllMachineTemplateForStorage();
//        List<List<CdnMachineTemplateDTO>> partition = ListUtils.partition(dtoList, 10);
//        CopyOnWriteArrayList<CompletableFuture<Void>> futures = new CopyOnWriteArrayList<>();
//        partition.forEach(item -> {
//            futures.add(CompletableFuture.runAsync(() -> {
//                item.forEach(this::updateOneTemplateCount);
//            }, executor));
//        });
//        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).join();
//        boolean delete = resManageTairManager.delete(CdnSyncMachineTemplateCountTimer.MACHINE_TEMPLATE_COUNT_KEY);
//    }
//
//}
