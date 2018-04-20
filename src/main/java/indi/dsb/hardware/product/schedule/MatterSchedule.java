package indi.dsb.hardware.product.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MatterSchedule {
    private static final Logger LOGGER = LoggerFactory.getLogger(MatterSchedule.class);

//    @Autowired
//    private MatterService matterService;
//
//    /**
//     * 检查OA单是否通过 
//     * 每30分钟执行一次
//     * @Title checkOAIsPass 
//     * @Description
//     */
//    @Scheduled(cron = "0 0/30 * * * ? ")
////    @Scheduled(cron="0 0/1 * * * ?") //每分钟执行一次  测试
//    public void checkOAIsPass() {
//        LOGGER.error("[checkOAIsPass] 开始执行");
//        
//        Example example = new Example(Matter.class);
//        example.createCriteria().andEqualTo("isDeleted", false).andEqualTo("processStatus", MatterStatus.APPLY_DELAYING.getCode());
//        List<Matter> matters = matterService.selectByExample(example);
//        JSONObject param = null;
//        for (Matter matter : matters) {
//            param = new JSONObject();
//            param.put("requestid", matter.getRemark());
//            
//            String paramStr = param.toJSONString();
//            String result = HttpUtil.postData(ApiContant.SERVICE_OA_WORKFLOW_GETWORKFLOW, paramStr, "application/json");
//            if (StringUtils.isBlank(result)) {
//                LOGGER.error("[getWorkflow] 接口异常");
//                LOGGER.error("[getWorkflow] url:" + ApiContant.SERVICE_OA_WORKFLOW_GETWORKFLOW);
//                LOGGER.error("[getWorkflow] data:" + paramStr);
//                LOGGER.error("[getWorkflow] result:" + result);
//                continue;
//            }
//            LOGGER.info("[getWorkflow] result:" + result);
//            JSONObject jsonResult = JSONObject.parseObject(result);
//            if (200 == jsonResult.getIntValue("code")) {
//                JSONObject resultData = jsonResult.getJSONObject("result");
//                if (null != resultData && !resultData.isEmpty()) {
//                    String status = resultData.getString("status");
//                    if ("审批通过".equals(status)) {
//                        Date expectTime = DateUtil.parseDisplayDatetime(resultData.getString("date")+" "+resultData.getString("time"));
//                        matter.setExpectTime(expectTime);
//                        matter.setProcessStatus(MatterStatus.WAIT_HANDLE.getCode());
//                        matterService.updateBySystem(matter);
//                    } else if ("审批不通过".equals(status)) {
//                        matter.setProcessStatus(MatterStatus.WAIT_HANDLE.getCode());
//                        matterService.updateBySystem(matter);
//                    }
//                }
//            }
//        }
//        LOGGER.error("[checkOAIsPass] 执行完成");
//    }

}
