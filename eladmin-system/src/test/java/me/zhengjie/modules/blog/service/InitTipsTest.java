package me.zhengjie.modules.blog.service;

import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.zhengjie.modules.blog.domain.Motto;
import me.zhengjie.modules.blog.domain.Tips;
import me.zhengjie.modules.blog.repository.MottoRepository;
import me.zhengjie.modules.blog.repository.TipsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author : Kahen
 * @date : 2020/12/27 21:14
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class InitTipsTest {

    @Autowired
    private MottoRepository mottoRepository;
    @Autowired
    private TipsRepository tipsRepository;

    private List<Tips> getTips(JSONObject jsonObject2) {
        List<Tips> tipssList = new ArrayList<>();
        for (Map.Entry<String, Object> categories : jsonObject2.getJSONObject("categories").entrySet()) {
            JSONArray jsonArray = (JSONArray) categories.getValue();
            for (Object o : jsonArray) {
                JSONObject jsonObject21 = (JSONObject) o;
                tipssList.add(new Tips().setId(IdUtil.simpleUUID()).setPeriodType(categories.getKey()).setTipType("通用")
                        .setContent(jsonObject21.getString("title")));
            }
        }
        return tipssList;
    }

    private List<Tips> getTips(JSONArray jsonArray) {
        List<Tips> tipsList = new ArrayList<>();
        for (Object o : jsonArray) {
            JSONObject jsonObject = (JSONObject) o;
            for (Object questions : jsonObject.getJSONArray("questions")) {
                tipsList.add(new Tips().setContent(questions.toString()).setTipType(jsonObject.getString("name"))
                        .setPeriodType("daily").setId(IdUtil.simpleUUID()));
            }
        }
        return tipsList;
    }

    @Test
    public void testInit() {
        List<Tips> tipsList = new ArrayList<>();
        List<Motto> mottoList = new ArrayList<>();
        String s = "{\"categories\":{\"yearly\":[{\"content\":\"\",\"title\":\"今年我最喜欢的一天/最喜欢的一件事是什么？\"}," +
                "{\"content\":\"\"," +
                "\"title\":\"今年有什么我喜欢的书/电影/文章/播客/专辑吗？\"},{\"content\":\"\",\"title\":\"我如何维持我的身体健康和精神状态？\"}," +
                "{\"content\":\"\",\"title\":\"今年有没有谁对我来说意义非凡？为什么？\"},{\"content\":\"\"," +
                "\"title\":\"今年我有发展新的爱好或兴趣吗？\"},{\"content\":\"\",\"title\":\"关于我自己，我最喜欢的一件事是什么？\"}]," +
                "\"daily\":[{\"content\":\"\",\"title\":\"今天我的精神状态如何？\"},{\"content\":\"\"," +
                "\"title\":\"今天我的身体状况如何？\"},{\"content\":\"\",\"title\":\"今天我早餐、午餐、晚餐吃了什么？\"},{\"content\":\"\"," +
                "\"title\":\"今天我完成了什么？\"},{\"content\":\"\",\"title\":\"今天我花了多少钱？\"},{\"content\":\"\"," +
                "\"title\":\"今天发生了什么特别的事情吗？\"}]},\"title\":\"\",\"image\":\"journal-covers/image-1\"," +
                "\"description\":\"\"}";
        JSONObject jsonObject = JSON.parseObject(s);
        for (Map.Entry<String, Object> categories : jsonObject.getJSONObject("categories").entrySet()) {
            JSONArray jsonArray = (JSONArray) categories.getValue();
            for (Object o : jsonArray) {
                JSONObject jsonObject1 = (JSONObject) o;
                tipsList.add(new Tips().setId(IdUtil.simpleUUID()).setPeriodType(categories.getKey()).setTipType("通用")
                        .setContent(jsonObject1.getString("title")));
            }
        }
        String s1 = "{\"categories\":{\"yearly\":[{\"content\":\"\",\"title\":\"今年对我最有启发的人是谁？他有哪些特质？\"}," +
                "{\"content\":\"\"," +
                "\"title\":\"今年我去了哪里旅行？我最喜欢的目的地是？\"},{\"content\":\"\",\"title\":\"是什么使我独一无二？\"},{\"content\":\"\"," +
                "\"title\":\"真实地生活对我来说意味着什么？\"},{\"content\":\"\",\"title\":\"如果没有人评价我，对某些事我是否会有不同的做法？\"}," +
                "{\"content\":\"\",\"title\":\"今年我最棒的创造/创作是什么？\"},{\"content\":\"\",\"title\":\"我想在生活的哪个方面更加有所成长？\"}," +
                "{\"content\":\"\",\"title\":\"如果明年我可以学习任何一门新的技能，那会是什么？\"}],\"daily\":[{\"content\":\"\"," +
                "\"title\":\"今日我要感恩什么？\"},{\"content\":\"\",\"title\":\"今天我运动了吗？\"},{\"content\":\"\"," +
                "\"title\":\"今天我完成了什么事？\"},{\"content\":\"\",\"title\":\"今天我花了多少钱？\"},{\"content\":\"\"," +
                "\"title\":\"关于今天的三件好事是什么？\"},{\"content\":\"\",\"title\":\"我如何能让明天变得更好？\"}]," +
                "\"monthly\":[{\"content\":\"\",\"title\":\"本月我感到最幸福的瞬间是：\"},{\"content\":\"\"," +
                "\"title\":\"本月有什么在阻止我的进步？\"},{\"content\":\"\",\"title\":\"我可以做些什么来更多地关注自己的健康？\"},{\"content\":\"\"," +
                "\"title\":\"我是否为我无法控制的事情而感到焦虑？\"},{\"content\":\"\",\"title\":\"下个月我可以向谁寻求支持和建议/引导？\"}," +
                "{\"content\":\"\",\"title\":\"下个月我所期待的事情是什么？\"}]},\"title\":\"\"," +
                "\"image\":\"journal-covers/image-2\",\"description\":\"\"}";
        JSONObject jsonObject1 = JSON.parseObject(s1);
        for (Map.Entry<String, Object> categories : jsonObject1.getJSONObject("categories").entrySet()) {
            JSONArray jsonArray = (JSONArray) categories.getValue();
            for (Object o : jsonArray) {
                JSONObject jsonObject21 = (JSONObject) o;
                tipsList.add(new Tips().setId(IdUtil.simpleUUID()).setPeriodType(categories.getKey()).setTipType("通用")
                        .setContent(jsonObject21.getString("title")));
            }
        }
        String s2 = "{\"categories\":{\"yearly\":[{\"content\":\"\",\"title\":\"什么可以让我感到平静？\"},{\"content\":\"\"," +
                "\"title\":\"我是一个快乐的人吗？\"},{\"content\":\"\",\"title\":\"对待我自己，我是否充满爱和尊敬？\"},{\"content\":\"\"," +
                "\"title\":\"我是否能很好地适应在”不舒服“的境况？\"},{\"content\":\"\",\"title\":\"能帮助我跨出自己”舒适圈“的三种方式：\"}," +
                "{\"content\":\"\",\"title\":\"我有需要原谅的人吗？\"},{\"content\":\"\"," +
                "\"title\":\"是否有这么一个人，我能与他\\/她谈论任何事？\"},{\"content\":\"\",\"title\":\"我可以做什么让我更加自信和快乐？\"}," +
                "{\"content\":\"\",\"title\":\"如果我要给一个新生儿提出一条建议，那会是什么？\"},{\"content\":\"\"," +
                "\"title\":\"我生活中的哪些部分无法反应出真实的我自己？\"},{\"content\":\"\",\"title\":\"我最爱的一件关于我自己的事是什么？\"}]," +
                "\"daily\":[{\"content\":\"\",\"title\":\"今日感恩：\"},{\"content\":\"\",\"title\":\"什么会让今天成为很棒的一天？\"}," +
                "{\"content\":\"\",\"title\":\"今日份自我肯定\"},{\"content\":\"1. \\n2. \\n3. \",\"title\":\"今天发生的三件事：\"}," +
                "{\"content\":\"\",\"title\":\"我还可以如何让今天变得更好？\"}]},\"title\":\"\"," +
                "\"image\":\"journal-covers\\/image-3\",\"description\":\"\"}";
        JSONObject jsonObject2 = JSON.parseObject(s2);
        for (Map.Entry<String, Object> categories : jsonObject2.getJSONObject("categories").entrySet()) {
            JSONArray jsonArray = (JSONArray) categories.getValue();
            for (Object o : jsonArray) {
                JSONObject jsonObject21 = (JSONObject) o;
                tipsList.add(new Tips().setId(IdUtil.simpleUUID()).setPeriodType(categories.getKey()).setTipType("通用")
                        .setContent(jsonObject21.getString("title")));
            }
        }
        String s3 = "{\"categories\":{\"weekly\":[{\"content\":\"\",\"title\":\"本周我最喜欢的瞬间是：\"},{\"content\":\"\"," +
                "\"title\":\"本周我最感谢的人或事：\"},{\"content\":\"\",\"title\":\"本周在关心自我方面，我都做了些什么？\"},{\"content\":\"\"," +
                "\"title\":\"我帮助他人的一件事：\"},{\"content\":\"\",\"title\":\"别人帮助我的一件事：\"},{\"content\":\"\"," +
                "\"title\":\"这周什么人或事让我受到启发或激励？\"},{\"content\":\"\",\"title\":\"这周我可以从什么正向的事情上汲取动力？\"}," +
                "{\"content\":\"\",\"title\":\"下周我可以努力做到释怀的事情是：\"}],\"yearly\":[{\"content\":\"\"," +
                "\"title\":\"今年我最喜欢的一天/最喜欢的一件事是什么？\"},{\"content\":\"\",\"title\":\"今年我创造的最棒的一件事是什么？\"}," +
                "{\"content\":\"\",\"title\":\"为了我和家人的未来，我做出的最有影响力的决定是：\"},{\"content\":\"\"," +
                "\"title\":\"今年我在财务上最棒的成就是什么？\"},{\"content\":\"\",\"title\":\"今年我学到的最艰难的一课是什么？\"},{\"content\":\"\"," +
                "\"title\":\"今年最让人沮丧的经历是什么？\"},{\"content\":\"\",\"title\":\"过去一年，我最感恩的一件事是什么？\"},{\"content\":\"\"," +
                "\"title\":\"我是在随波逐流，还是在听从我自己的内心？\"}],\"daily\":[{\"content\":\"\",\"title\":\"今日感恩：\"}," +
                "{\"content\":\"\",\"title\":\"什么会让今天成为很棒的一天？\"},{\"content\":\"\",\"title\":\"今日份自我肯定\"}," +
                "{\"content\":\"1. \\n2. \\n3. \",\"title\":\"今天发生的三件事：\"},{\"content\":\"\"," +
                "\"title\":\"我还可以如何让今天变得更好？\"}]},\"title\":\"\",\"image\":\"journal-covers/image-4\"," +
                "\"description\":\"\"}";
        JSONObject jsonObject3 = JSON.parseObject(s3);
        for (Map.Entry<String, Object> categories : jsonObject3.getJSONObject("categories").entrySet()) {
            JSONArray jsonArray = (JSONArray) categories.getValue();
            for (Object o : jsonArray) {
                JSONObject jsonObject21 = (JSONObject) o;
                tipsList.add(new Tips().setId(IdUtil.simpleUUID()).setPeriodType(categories.getKey()).setTipType("通用")
                        .setContent(jsonObject21.getString("title")));
            }
        }
        String s4 = "{\"categories\":{\"yearly\":[{\"content\":\"\",\"title\":\"过去一年，我学到的最艰难的一课是：\"}," +
                "{\"content\":\"\"," +
                "\"title\":\"新的一年，我希望我可以戒掉的习惯是：\"},{\"content\":\"\"," +
                "\"title\":\"新的一年，我的个人\\/家庭\\/健康\\/财务\\/职业目标是什么？\"},{\"content\":\"\"," +
                "\"title\":\"如果人生没有任何限制，我会做什么？\"},{\"content\":\"\",\"title\":\"我现在过的生活是我想要的生活吗？\"}," +
                "{\"content\":\"\",\"title\":\"今年我有发展新的爱好或兴趣吗？\"},{\"content\":\"\",\"title\":\"我想在什么事情上投入更多的时间？\"}," +
                "{\"content\":\"\",\"title\":\"如果今年一切顺利会是什么样子？\"}],\"daily\":[{\"content\":\"- [ ] 写日记\\n- [ ] 读书\\n-" +
                " [ ] 冥想\\n- [ ] 锻炼\",\"title\":\"习惯打卡\"},{\"content\":\"1. \\n2. \\n3. \"," +
                "\"title\":\"今天我必须要做的三件事是：\"},{\"content\":\"\",\"title\":\"今日感恩：\"},{\"content\":\"\"," +
                "\"title\":\"我还可以如何让今天变得更好？\"}]},\"title\":\"\",\"image\":\"journal-covers\\/image-5\"," +
                "\"description\":\"\"}";
        JSONObject jsonObject4 = JSON.parseObject(s4);
        tipsList.addAll(getTips(jsonObject4));
        String s5 = "{\"categories\":{\"weekly\":[{\"content\":\"- [ ] 清空收件箱\\n- [ ] 回顾进行中的项目\\n- [ ] 回顾本周日记\\n- [ ] " +
                "回顾本周日程\\n- [" +
                " ] 设定下周目标\",\"title\":\"本周任务清单\"},{\"content\":\"\",\"title\":\"本周成就\"},{\"content\":\"\"," +
                "\"title\":\"本周没有完成的事项\"},{\"content\":\"\",\"title\":\"本周我学到了什么？\"}],\"daily\":[{\"content\":\"\"," +
                "\"title\":\"今日目标\"},{\"content\":\"\",\"title\":\"今日成就\"},{\"content\":\"\",\"title\":\"日间例行安排\"}," +
                "{\"content\":\"\",\"title\":\"晚间例行安排\"},{\"content\":\"\",\"title\":\"本周我学到了：\"},{\"content\":\"\"," +
                "\"title\":\"今日感恩：\"}],\"monthly\":[{\"content\":\"\",\"title\":\"这个月，我要：\"},{\"content\":\"\"," +
                "\"title\":\"下个月，我想要：\"},{\"content\":\"\",\"title\":\"我想要实现这些事，因为：\"},{\"content\":\"\"," +
                "\"title\":\"我实现它们的计划是：\"},{\"content\":\"\",\"title\":\"如果实现它们，我给自己的奖励是：\"},{\"content\":\"\"," +
                "\"title\":\"本月反思：\"}]},\"title\":\"\",\"image\":\"journal-covers\\/image-6\",\"description\":\"\"}";

        JSONObject jsonObject5 = JSON.parseObject(s5);
        tipsList.addAll(getTips(jsonObject5));

        String s6 = "{\"categories\":{\"weekly\":[{\"content\":\"\",\"title\":\"本周聚焦：\"},{\"content\":\"\"," +
                "\"title\":\"本周已实现的目标：\"},{\"content\":\"\",\"title\":\"我最近的举动是否让我离我的目标更近一步？\"},{\"content\":\"\"," +
                "\"title\":\"下一周我想要实现的目标：\"}],\"yearly\":[{\"content\":\"\",\"title\":\"今年我想完成的最大的目标是：\"}," +
                "{\"content\":\"\",\"title\":\"今年到现在为止进行得如何？\"},{\"content\":\"\",\"title\":\"今年我遇到的最大的挑战是：\"}," +
                "{\"content\":\"\",\"title\":\"今年我最大的欢乐来自于：\"},{\"content\":\"\",\"title\":\"我想在什么事情上投入更多的时间？\"}," +
                "{\"content\":\"\",\"title\":\"我现在过的生活是我想要的生活吗？\"},{\"content\":\"\",\"title\":\"如果今年一切顺利会是什么样子？\"}," +
                "{\"content\":\"1. \\n2. \\n3. \",\"title\":\"明年我最想实现的三个目标是：\"}],\"daily\":[{\"content\":\"\"," +
                "\"title\":\"今天我致力于：\"},{\"content\":\"\",\"title\":\"今日感恩：\"},{\"content\":\"1. \\n2. \\n3. \"," +
                "\"title\":\"今天我必须要做的三件事是：\"},{\"content\":\"\",\"title\":\"今年到现在为止进行得如何？\"}]},\"title\":\"\"," +
                "\"image\":\"journal-covers\\/image-7\",\"description\":\"\"}";
        JSONObject jsonObject6 = JSON.parseObject(s6);
        tipsList.addAll(getTips(jsonObject6));

        String s7 = "{\"categories\":{\"weekly\":[{\"content\":\"\",\"title\":\"本周聚焦：\"},{\"content\":\"\"," +
                "\"title\":\"本周已实现的目标：\"},{\"content\":\"\",\"title\":\"个人待办事项\"},{\"content\":\"\"," +
                "\"title\":\"工作待办事项\"},{\"content\":\"- [ ] 年度目标\\n- [ ] 月度目标\\n- [ ] 上一周的进展\"," +
                "\"title\":\"本周任务清单\"}],\"yearly\":[{\"content\":\"\",\"title\":\"本年度主题\"},{\"content\":\"\"," +
                "\"title\":\"本年度目标\"},{\"content\":\"\",\"title\":\"如果今年一切顺利会是什么样子？\"},{\"content\":\"\"," +
                "\"title\":\"今年我想开始做的事是什么？\"},{\"content\":\"\",\"title\":\"今年我完成的最大目标是什么？\"}]," +
                "\"daily\":[{\"content\":\"* 8:00\\n* 9:00\\n* 10:00\",\"title\":\"今日时间线\"},{\"content\":\"\"," +
                "\"title\":\"今日聚焦\"},{\"content\":\"\",\"title\":\"个人待办事项\"},{\"content\":\"\",\"title\":\"工作待办事项\"}," +
                "{\"content\":\"\",\"title\":\"今天发生的最棒的事：\"},{\"content\":\"\",\"title\":\"今天我学到了：\"}]," +
                "\"monthly\":[{\"content\":\"\",\"title\":\"本月目标\"},{\"content\":\"\",\"title\":\"行动计划：\"}," +
                "{\"content\":\"\",\"title\":\"本月我完成\\/未完成我的目标，为什么？\"},{\"content\":\"\"," +
                "\"title\":\"本月我给我自己向着目标的努力打几分？（1→10）\"},{\"content\":\"\",\"title\":\"下一步，我的计划是：\"},{\"content\":\"1" +
                ". \\n2. \\n3. \",\"title\":\"本月，我承诺：\"},{\"content\":\"\",\"title\":\"如果实现它们，我给自己的奖励是：\"}]}," +
                "\"title\":\"\",\"image\":\"journal-covers\\/image-8\",\"description\":\"\"}";
        JSONObject jsonObject7 = JSON.parseObject(s7);
        tipsList.addAll(getTips(jsonObject7));

        String s8 = "{\"categories\":{\"daily\":[{\"content\":\"\",\"title\":\"今日成就\"},{\"content\":\"- [ ] \\n- [ ] " +
                "\\n- [ ] \"," +
                "\"title\":\"今日待办事项\"},{\"content\":\"\",\"title\":\"健康与锻炼\"},{\"content\":\"\",\"title\":\"个人成长\"}," +
                "{\"content\":\"\",\"title\":\"婚姻\\/伴侣\"},{\"content\":\"\",\"title\":\"家庭\\/朋友\"},{\"content\":\"\"," +
                "\"title\":\"职业\"},{\"content\":\"\",\"title\":\"财务\"}]},\"title\":\"\"," +
                "\"image\":\"journal-covers\\/image-9\",\"description\":\"\"}";
        JSONObject jsonObject8 = JSON.parseObject(s8);
        tipsList.addAll(getTips(jsonObject8));

        String s9 = "{\"categories\":{\"weekly\":[{\"content\":\"- [ ] 清空收件箱\\n- [ ] 回顾进行中的项目\\n- [ ] 回顾本周日记\\n- [ ] " +
                "回顾本周日程\\n- [" +
                " ] 设定下周目标\",\"title\":\"本周回顾清单\"},{\"content\":\"\",\"title\":\"专业发展\"},{\"content\":\"\"," +
                "\"title\":\"个人发展\"},{\"content\":\"\",\"title\":\"本周我学到了什么？\"},{\"content\":\"\"," +
                "\"title\":\"本周我最大的成就是什么？\"},{\"content\":\"- [ ] \\n- [ ] \\n- [ ] \",\"title\":\"下周待办事项\"}]," +
                "\"yearly\":[{\"content\":\"\",\"title\":\"身体健康\"},{\"content\":\"\",\"title\":\"心智健康\"}," +
                "{\"content\":\"\",\"title\":\"个人发展\"},{\"content\":\"\",\"title\":\"婚姻\\/伴侣\"},{\"content\":\"\"," +
                "\"title\":\"家庭\\/朋友\"},{\"content\":\"\",\"title\":\"职业\"},{\"content\":\"\",\"title\":\"乐趣\"}," +
                "{\"content\":\"\",\"title\":\"冒险\\/旅行\"},{\"content\":\"\",\"title\":\"社区\"},{\"content\":\"\"," +
                "\"title\":\"财务\"}],\"daily\":[{\"content\":\"\",\"title\":\"今日成就\"},{\"content\":\"- [ ] \\n- [ ] " +
                "\\n- [ ] \",\"title\":\"今日待办事项\"},{\"content\":\"\",\"title\":\"健康\\/锻炼\"},{\"content\":\"\"," +
                "\"title\":\"个人成长\"},{\"content\":\"\",\"title\":\"婚姻\\/伴侣\"},{\"content\":\"\"," +
                "\"title\":\"家庭\\/朋友\"},{\"content\":\"\",\"title\":\"职业\"},{\"content\":\"\",\"title\":\"财务\"}]," +
                "\"monthly\":[{\"content\":\"\",\"title\":\"本月回顾清单\"},{\"content\":\"\",\"title\":\"个人项目\"}," +
                "{\"content\":\"\",\"title\":\"工作项目\"},{\"content\":\"\",\"title\":\"难忘的瞬间\"},{\"content\":\"\"," +
                "\"title\":\"我所感到骄傲的事：\"},{\"content\":\"\",\"title\":\"最大的教训：\"}]},\"title\":\"\"," +
                "\"image\":\"journal-covers\\/image-10\",\"description\":\"\"}";
        JSONObject jsonObject9 = JSON.parseObject(s9);
        tipsList.addAll(getTips(jsonObject9));

        String s10 = "[{\"name\":\"特殊日子\",\"questions\":[\"今天是我来到这个世界的第几天？\",\"我想做什么让今天变得特别？\",\"今天发生了哪些值得关注的事？\"," +
                "\"今天是我第一次完成什么体验的日子？\",\"今天我因为什么节日/纪念日而做了什么？\",\"今天是我因为什么而送/收某人什么礼物的日子？\",\"今天小确幸\",\"今日灵感\"," +
                "\"今天触动我的一件事\",\"今日感恩\"]},{\"name\":\"健康\",\"questions\":[\"今天我做了什么运动？\",\"今天我吃了什么？\",\"今天我冥想了吗？\"," +
                "\"今天我健身了吗？\",\"今天我梦见了什么？\",\"今天我的精神状态如何？\",\"今天我的身体状况如何？\",\"今天我睡得好不好？\",\"今天我几点起床/睡觉/冥想？\"," +
                "\"今天我喝了足够的水吗？\",\"今天我的排便正常吗？\",\"一日三餐\",\"我的梦境\"]},{\"name\":\"社交往来\"," +
                "\"questions\":[\"今天我参加了什么活动？\",\"今天我和谁交流了什么？\",\"今天我收到什么惊喜吗？\",\"今天我帮助谁做了什么？\",\"今天我很感谢谁给我的帮助？\"," +
                "\"今天我最有感触的是谁的什么行为/话语？\",\"今天我喜欢/讨厌与谁的相处模式？\",\"今天我关心或被谁关心了，心里的感受是什么？\",\"我的亲密关系\"]}," +
                "{\"name\":\"事业发展\",\"questions\":[\"今天我遇到了什么工作难题并如何解决？\",\"今天我完成了什么工作？\",\"今天我是如何对待工作的？\"," +
                "\"今天我在工作中学到了什么？\",\"今天我在工作上获得了什么成绩？\",\"今天我在工作中需要改变什么方法/技能/行动？\",\"今天我与同事的关系如何？\"]}," +
                "{\"name\":\"财务自由\",\"questions\":[\"今天我为实现财务自由做了什么？\",\"今天我记账了吗？\",\"今天我因为什么赚了多少钱？\"," +
                "\"今天我的资产盘点状况如何？\",\"今天我做了什么投资？\",\"今天我的投资收益怎么样？\",\"今日收入和支出\"]},{\"name\":\"家庭幸福\"," +
                "\"questions\":[\"今天我陪伴家人一起做了什么事？\",\"今天我为家人做了哪些事？\",\"今天家人为我做了什么事？\",\"今天是否有向家人表达我的爱意？\"," +
                "\"今天我的宠物/植物发生了什么有意思的事？\",\"今天我愿意做什么以便让家庭可以更美满？\",\"今天我学到了什么可以让家庭更幸福的秘诀？\"]},{\"name\":\"个人成长\"," +
                "\"questions\":[\"逆水行舟，不进则退，今天我的进步是什么？\",\"今天我犯的错误是什么？\",\"今天我坚持了哪些好习惯？\",\"今天让我很有收获的是什么书/画/音乐/视频？\"," +
                "\"如果用一个句子描述今天的我会是什么？\",\"什么不会让我成长，或不会带给我长远的意义？\",\"今天让我幸福/感动/痛苦/恐惧的人/事/物是什么？\",\"今天探寻到什么世界真理？\"," +
                "\"今天我学到了什么？\",\"今天我突破舒适区了吗？\"]},{\"name\":\"计划目标\",\"questions\":[\"明天必须完成的三件事是什么？\"," +
                "\"今天我将全力以赴完成什么？\",\"今天我拖拖拉拉是因为什么？\",\"今天我因为什么而调整我的日/周/月/年目标？\",\"今天我为日/周/月/年目标做了什么努力？\"," +
                "\"今天我在关注的领域取得了什么样的成绩？\",\"今天有哪些分心/浪费时间的事，让我无法更有效率？\"]},{\"name\":\"梦想实现\"," +
                "\"questions\":[\"今天我要怎么行动才能让我的未来更美好？\",\"今天的我是最好的自己吗？\",\"还记得我的理想是什么吗？\",\"今天我为改变世界做了些什么？\"," +
                "\"今天我愿意放弃什么以便让梦想可以实现？\",\"今天有什么灵感/信息可以让我的梦想更进一步？\",\"从更广的层面来看，什么事情并非真的那么重要？\"]}]";
        JSONArray jsonArray = JSON.parseArray(s10);
        tipsList.addAll(getTips(jsonArray));
        String s11 = "[{\"name\":\"通用\",\"questions\":[\"本⽉最值得纪念的事情是什么？\",\"本月为实现年度目标做了什么？\",\"本月是否有新的想法或不错的创意?\"," +
                "\"本月克服了哪些挑战?\"," +
                "\"本月最应该感谢的人是谁?\",\"本月我与哪些人保持联系?\",\"本月最值得我自豪的事情是什么？\",\"本月有什么事情让我退缩？\",\"本月我发现了什么机会点？\"," +
                "\"本月我最大的成就是什么？\",\"我是否是为了开心和成就感在工作？\",\"本月我是否更加健康？\",\"我是否在真正重要的事情上投入了更多的时间？如何改进？\"," +
                "\"如何让下个月的目标进展更加顺利？\",\"本月我读过哪些书？\",\"下月我期待会发生什么？\",\"下月我想要联系哪些人？\",\"下月我需要改进的3个点\",\"月度计划是否如期进行？\"," +
                "\"本月我最喜欢的一天或一件事\",\"我的月度最佳电影\",\"我的月度最佳图书\",\"我的月度最佳音乐\",\"本月工作总结\",\"本月学习总结\",\"本月生活总结\"," +
                "\"下月要实现的目标\"]}]";

        JSONArray jsonArray11 = JSON.parseArray(s11);
        tipsList.addAll(getTips(jsonArray11));

        String s12 = "[{\"name\":\"通用\",\"questions\":[\"本周我最大的成就是什么？\",\"本周是否有新的想法或不错的创意?\",\"本周克服了哪些挑战?\"," +
                "\"本周最应该感谢的人是谁?\"," +
                "\"本周哪一天浪费了最多时间？\",\"本周发⽣的最美好的事是什么？\",\"本周最值得我自豪的事情是什么？\",\"本周有什么事情让我退缩?\",\"本周我发现了什么机会点？\"," +
                "\"本周最后悔的事情是什么？\",\"本周最值得纪念的是什么？\",\"我与最亲密的人相处了多长时间？\",\"上次我对自己说“我爱你”是什么时候？\",\"本周我是否大部分时间都比较自信？\"," +
                "\"对我的合作伙伴与客户而言本周我的影响力如何？\",\"本周是否经历了沮丧和烦闷的时刻？\",\"本周遇到什么事情让我无法在工作上全力以赴？\",\"我是否行走在正确的道路上？\"," +
                "\"我在害怕什么？\",\"本周最重要的一件事是什么？\",\"本周在哪些事情上我在拖延？\",\"下周我可以帮助谁？\",\"我是否是为了开心和成就感在工作？\",\"本周的⼩确幸\"," +
                "\"本周5件值得多做的事\",\"本周5件应该少做的事\",\"本周的工作总结\",\"本周的生活总结\",\"本周的学习总结\",\"本周的身体状况\",\"下周任务清单\"," +
                "\"下周需要改进的3个点\",\"下周我期待发生什么？\"]}]";
        JSONArray jsonArray12 = JSON.parseArray(s12);
        tipsList.addAll(getTips(jsonArray12));

        String s13 = "[{\"name\":\"通用\",\"questions\":[\"我的年度头条事件是什么？\",\"今年我最大的成就是什么？\",\"今年我做的最重要的决定是什么？\"," +
                "\"明年最大的心愿是什么？\"," +
                "\"你会对十年前的自⼰说些什么？\",\"如果让我给新生儿一条人生建议，我会说什么？\",\"今年为实现五年计划做了什么？\",\"今年是否有实现一项人生梦想？\",\"今年养成了什么好习惯？\"," +
                "\"今年戒掉了什么坏习惯？\",\"明年要养成什么新习惯？\",\"今年学到的最重要的教训是什么？\",\"今年最值得感谢的是谁？\",\"我是否对什么人或事过于执着？\"," +
                "\"我真正想要过的生活是什么？\",\"我今年的财务目标实现了吗？\",\"我想要追求的极限体验是什么？\",\"我快乐吗？\",\"我有罪恶感吗？\",\"我积极乐观吗？\"," +
                "\"我有更深刻的认识自己吗？\",\"我比一年前的自己更好吗？\",\"我可以原谅那些带给我伤害的人吗？\",\"我为身边的人带来了什么价值？\",\"我可以做什么来走出舒适区？\"," +
                "\"倾听内心的声音，我是否一直在忽略什么感受？\",\"我拥有能够倾诉所有事情的对象吗？\",\"上次对某个人说“我爱你”是什么时候？\",\"上次我对自己说“我爱你”是什么时候？\"," +
                "\"今年我相处最多的五个人是谁？\",\"明年此时我希望置身何处？\",\"什么事物给予我灵感？\",\"我可以如何增加自信？\",\"有什么限制使我一直无法实现我想要的目标？\"," +
                "\"今年是否发生了改变我人生轨迹的大事？\",\"今年我做出的最重大的决定是什么？\",\"我今年去过哪些地方？\",\"我的年度最佳电影\",\"我的年度最佳图书\",\"我的年度最佳音乐\"," +
                "\"年度工作总结\",\"年度生活总结\",\"年度学习总结\",\"我的年度计划复盘\",\"新年目标\"]}]";
        JSONArray jsonArray13 = JSON.parseArray(s13);
        tipsList.addAll(getTips(jsonArray13));

        String s14 = "[{\"content\":\"要对生命感到喜悦，因为它给了你去爱的机会，去工作，去玩乐，并用能仰头看星星的机会。\",\"author\":\"享利·凡·戴克\"}," +
                "{\"content\":\"真正的朋友应该说真话，不管那话多么尖锐。\",\"author\":\"奥斯特洛夫斯基\"}," +
                "{\"content\":\"未经开垦的心田犹如那未经开垦的荒地，杂草丛生；而荒地一经开垦，燕以辛勤的汗水浇灌，就会开出绚丽夺目的花朵。\",\"author\":\"佚名\"}," +
                "{\"content\":\"人的威严蕴藏在知识之中，因此，人有许多君主的金银无法买到，君主的武力不可征服内在的东西。\",\"author\":\"培根\"}," +
                "{\"content\":\"意志的出现不是对愿望的否定，而是把愿望合并和提升到一个更高的意识水平上。\",\"author\":\"罗洛·梅\"}," +
                "{\"content\":\"如果迟缓的落日照耀到你的手，而你发觉当天并没有做过有价值的事，那天便应视为已经失落。\",\"author\":\"杨格\"}," +
                "{\"content\":\"实践，是个伟大的揭发者，它暴露一切欺人和自欺。\",\"author\":\"车尔尼雪夫斯基\"}," +
                "{\"content\":\"希望象发芽的种子，在你心头充满春意；希望是甜蜜的，她象母亲怀抱中的婴儿，充满了生命的活力。\",\"author\":\"佚名\"}," +
                "{\"content\":\"灾难——这东西最能经常而确凿无疑地提醒我们，人生的事物并不全是按照我们自己的安排。有两种：自己的背运，还有别人的走运。\",\"author\":\"比尔斯\"}," +
                "{\"content\":\"无论哪个时代，青年的特点总是怀抱着各种理想和幻想。这并不是什么毛病，而是一种宝贵的品质。\",\"author\":\"加里宁\"}," +
                "{\"content\":\"命运是一个伟大的雕塑家，它举起人生的斧，凿在我们身上敲敲打打。她偏爱那些经过她精雕细刻的人。\",\"author\":\"佚名\"}," +
                "{\"content\":\"即将来临的一天，比过去的一年更为悠长。\",\"author\":\"福尔斯特\"}," +
                "{\"content\":\"现实是此岸，理想是彼岸。中间隔着湍急的河流，行动则是架在川上的桥梁。\",\"author\":\"克雷洛夫\"}," +
                "{\"content\":\"为自己寻求庸俗乏味的生活的人，才是真正可怜而渺小的。\",\"author\":\"约尔旦\"}," +
                "{\"content\":\"每个人都受两种教育，一种来自别人，另一种更重要的是来自自己。\",\"author\":\"爱德华·吉本\"}," +
                "{\"content\":\"你在活着的同时，也在学习着，无论如何，你活着。\",\"author\":\"道格拉斯·亚当斯\"}," +
                "{\"content\":\"我们探求真理，在一切事件中，获得真理是最高的快慰。\",\"author\":\"桑塔耶纳\"}," +
                "{\"content\":\"人的聪明和自己的明智及道路的选择，往往在失败以后。\",\"author\":\"贾曦光\"}," +
                "{\"content\":\"凡是希望荣誉而舒适地度过晚年的人，他必须在年轻时想到有一天会衰老；这样，在年老时，他也会记得曾有过年轻。\",\"author\":\"爱迪生\"}," +
                "{\"content\":\"有两种模糊：一种源于思想感情的贫乏，只能用语言来替代思想感情；另一种源于语言的贫乏，语言不足以表达丰富的感情。\",\"author\":\"普希金\"}," +
                "{\"content\":\"人生最低的境界是平凡，其次是超凡脱俗，最高是返璞归真的平凡。\",\"author\":\"周国平\"}," +
                "{\"content\":\"不安于小成，然后足以成大器；不诱于小利，然后可以立远功。\",\"author\":\"方孝孺\"}," +
                "{\"content\":\"我一定要把内心深处的东西释放出来，这就是我作曲的原因。\",\"author\":\"贝多芬\"}," +
                "{\"content\":\"今天只是未来生命中的一天，但你的未来却取决于你今天做了什么。\",\"author\":\"海明威\"}]";
        JSONArray jsonArray14 = JSON.parseArray(s14);
        for (Object o : jsonArray14) {
            JSONObject object = (JSONObject) o;
            mottoList.add(new Motto().setId(IdUtil.simpleUUID()).setType("励志").setAuthor(object.getString("author")).setContent(object.getString("content")));
        }
        String s15 = "[{\"title\":\"今日感恩与成功\",\"content\":\"\"},{\"title\":\"健康与饮食\",\"content\":\"\"}," +
                "{\"title\":\"工作和事业\"," +
                "\"content\":\"\"},{\"title\":\"家人和生活\",\"content\":\"\"},{\"title\":\"朋友和人际关系\",\"content\":\"\"}," +
                "{\"title\":\"支出和理财\",\"content\":\"\"},{\"title\":\"学习和成长\",\"content\":\"\"}," +
                "{\"title\":\"今日主要任务清单\",\"content\":\"\"}]";
        JSONArray jsonArray15 = JSON.parseArray(s15);
        for (Object o : jsonArray15) {
            JSONObject jsonObject10 = (JSONObject) o;
            tipsList.add(new Tips().setContent(jsonObject10.getString("title")).setPeriodType("diary").setTipType(
                    "通常").setId(IdUtil.simpleUUID()));

        }
        String s16 = "[{\"title\":\"本月总体分析\",\"content\":\"\"},{\"title\":\"本月最值得纪念的事情是什么？\",\"content\":\"\"}," +
                "{\"title\":\"本月为实现年度计划做了什么？\",\"content\":\"\"},{\"title\":\"下个月要改进的地方\",\"content\":\"\"}]";
        JSONArray jsonArray16 = JSON.parseArray(s16);
        for (Object o : jsonArray16) {
            JSONObject jsonObject10 = (JSONObject) o;
            tipsList.add(new Tips().setContent(jsonObject10.getString("title")).setPeriodType("monthly").setTipType(
                    "通常").setId(IdUtil.simpleUUID()));

        }
        String s17 = "[{\"title\":\"本周任务检查\",\"content\":\"- [ ] 清空收件箱\\n- [ ] 回顾项目清单\\n- [ ] 回顾本周日记\\n- [ ] 回顾日历\\n-" +
                " [ ] " +
                "设定下周目标\"},{\"title\":\"本周工作总结\",\"content\":\"\"},{\"title\":\"本周生活总结\",\"content\":\"\"}," +
                "{\"title\":\"本周最大的收获\",\"content\":\"\"},{\"title\":\"下周需要改进的地方\",\"content\":\"\"}," +
                "{\"title\":\"下周主要任务清单\",\"content\":\"\"}]";
        JSONArray jsonArray17 = JSON.parseArray(s17);
        for (Object o : jsonArray17) {
            JSONObject jsonObject10 = (JSONObject) o;
            tipsList.add(new Tips().setContent(jsonObject10.getString("title")).setPeriodType("weekly").setTipType(
                    "通常").setId(IdUtil.simpleUUID()));

        }
        String s18 = "[{\"title\":\"年度回顾\",\"content\":\"- [ ] 回顾年度目标\\n- [ ] 回顾全年日记\\n- [ ] 回顾全年日历\\n- [ ] " +
                "回顾全年照片\\n- [ ] " +
                "设定新年目标\"},{\"title\":\"年度目标完成情况总结\",\"content\":\"\"},{\"title\":\"年度最佳\",\"content\":\"## " +
                "电影\\n\\n## 图书\\n\\n## 音乐\\n\"},{\"title\":\"新年目标\",\"content\":\"\"}]";
        JSONArray jsonArray18 = JSON.parseArray(s18);
        for (Object o : jsonArray17) {
            JSONObject jsonObject10 = (JSONObject) o;
            tipsList.add(new Tips().setContent(jsonObject10.getString("title")).setPeriodType("yearly").setTipType(
                    "通常").setId(IdUtil.simpleUUID()));

        }

        mottoRepository.saveAll(mottoList);
        tipsRepository.saveAll(tipsList);
    }
}
