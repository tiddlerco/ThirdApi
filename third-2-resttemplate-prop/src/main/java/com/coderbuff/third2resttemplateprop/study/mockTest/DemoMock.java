package com.coderbuff.third2resttemplateprop.study.mockTest;

import com.coderbuff.third2resttemplateprop.study.mockTest.entity.StorageMachineInfoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author 喻可
 * @Date 2021/12/2 11:01
 */
@Service
public class DemoMock {

    public List<StorageMachineInfoDTO> mockMachineDate() {

        List<StorageMachineInfoDTO> list = new ArrayList<>();
        return list;
    }


    public List<StorageMachineInfoDTO> getList(){
        return mockMachineDate();
    }
}
