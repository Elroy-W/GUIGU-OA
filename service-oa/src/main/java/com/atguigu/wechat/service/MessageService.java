package com.atguigu.wechat.service;

/**
 * ClassName: MessageService
 * Package: com.atguigu.wechat.service
 * Description:
 *
 * @Author Elroy
 * @Create 2023/7/18 2:07
 * @Version 1.0
 */
public interface MessageService {
    /**
     * 推送待审批人员
     * @param processId
     * @param userId
     * @param taskId
     */
    void pushPendingMessage(Long processId, Long userId, String taskId);

    /**
     * 审批后推送提交审批人员
     * @param processId
     * @param userId
     * @param status
     */
    void pushProcessedMessage(Long processId, Long userId, Integer status);
}
