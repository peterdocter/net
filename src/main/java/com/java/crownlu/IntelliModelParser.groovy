package com.java.crownlu

import org.mvel2.MVEL
import org.mvel2.ParserContext

import java.time.LocalDate

/**
 * Created by crownlu on 17/4/11.
 */
class IntelliModelParser {
    static Map<String, String> map = [
            "title": "ticket.fromStation + ' — ' + ticket.toStation",
            "subtitle1": '''
                    if (ticket.displayStatus == 50) {
                        return ticket.trainCode + ' | ' + ticket.seatTypeName
                                + (ticket.ticketCount > 1 ? ' | ' + ticket.ticketCount : '');
                    } else if (ticket.displayStatus == 60) {
                        return '到达时间 ' + ticket.arriveTime;
                    } else {
                        return ticket.startTime + ' 出发';
                    }
            ''',

            "subtitle3": '''
                    if (ticket.displayStatus == 50)
                        return '总价: ￥' + ticket.totalPrice;
                    else if (ticket.displayStatus == 60)
                        return '下一站 ' + ticket.nextStation + ' ' + ticket.arriveNextTime;
                    else
                        return ticket.trainCode + ' | ' + ticket.seatTypeName + (ticket.ticketCount > 1 ? ' | 共' + ticket.ticketCount + '张' : ticket.coachNo + '车' + ticket.seatNo);
            ''',

            "text2": "ticket.displayStatus == 50 ? ticket.start_time : ''",
            "attachedInfo": "'待支付'",
            "icon4Color": "'#FF9E05'",
            "icon4Text": "ticket.displayStatus == 50 ? '' : ticket.displayStatus == 60 ? '时刻表' : '去支付'",
            "rightClickUrl": "ticket.displayStatus == 50 ? '' : ticket.displayStatus == 60 ? ticket.timetableUrl : ticket.orderUrl",
            "icon4TextColor": "'#FFFFFF'",
            "icon4V2List": "{new com.java.crownlu.InteligentShowModel.Icon4V2()}"

    ]

    InteligentShowModel parse2ticket(Train.train_ticket ticket, Map<String, String> exp) {
        InteligentShowModel m = new InteligentShowModel("tktrain", true)

        m
    }

    static def main(def args) {
        println("05:09".substring(0, 5))
//        ParserContext pcontext = new ParserContext()
//        pcontext.addImport(InteligentShowModel.class)
//        Serializable expression = MVEL.compileExpression("new InteligentShowModel('poi', true)", pcontext)
//        def x = MVEL.executeExpression(expression)

        ParserContext pcontext = new ParserContext()
        pcontext.addImport("lo", LocalDate.class)
        Serializable exp = MVEL.compileExpression("lo.parse('2017-04-02').month.value", pcontext)
        def x = MVEL.executeExpression(exp)
//        def x = MVEL.eval("DATE.parse('2017-04-02').month.value", ["DATE": LocalDate.class, "TIME": LocalTime.class]);
        println(x)

        Train.train_ticket.Builder t = Train.train_ticket.newBuilder()
        t.displayStatus = 10
        t.fromStation = "fromStation"
        t.toStation = "toStation"
        t.setCoachNo("coachNo")
        t.setSeatNo("seatNo")
        t.setStartTime("startTime")
        t.setTrainCode("trainCode")
        t.setSeatTypeName("seatTypeName")
        def o = t.build()
        InteligentShowModel m = new InteligentShowModel("tktrain", true)

        for (String k : map.keySet()) {
            try {
//                Serializable expression = MVEL.compileExpression(map[k])
                def context = ["ticket": o]
                m[k] = MVEL.eval(map[k], context);
                println(k + ":  " + m[k])
            } catch (def e) {
                printf(k + "-----" + map[k])
                e.printStackTrace()
            }

        }
        print(m)
    }
}
