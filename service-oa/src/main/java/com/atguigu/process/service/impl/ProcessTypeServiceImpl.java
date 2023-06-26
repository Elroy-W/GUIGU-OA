package com.atguigu.process.service.impl;

import com.atguigu.model.process.ProcessType;
import com.atguigu.process.mapper.ProcessTypeMapper;
import com.atguigu.process.service.ProcessTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 审批类型 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2023-06-26
 */
@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class ProcessTypeServiceImpl extends ServiceImpl<ProcessTypeMapper, ProcessType> implements ProcessTypeService {

}
