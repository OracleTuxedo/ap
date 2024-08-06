package mti.com.telegram.util;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import mti.com.telegram.vo.TelegramData;
import mti.com.telegram.vo.TelegramDataList;
import mti.com.telegram.vo.TelegramDataOut;
import mti.com.telegram.vo.TelegramDataOutList;
import mti.com.telegram.vo.TelegramHeader;
import mti.com.telegram.vo.TelegramIn;
import mti.com.telegram.vo.TelegramInList;
import mti.com.telegram.vo.TelegramInputUserData;
import mti.com.telegram.vo.TelegramMessage;
import mti.com.telegram.vo.TelegramOut;
import mti.com.telegram.vo.TelegramOutList;
import mti.com.telegram.vo.TelegramOutNoData;
import mti.com.telegram.vo.TelegramTail;
import mti.com.utility.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TelegramBuilder {
    private static final Logger logger = LogManager.getLogger(TelegramBuilder.class);

    public TelegramIn getTelegramIn(TelegramInputUserData paramTelegramInputUserData, Object paramObject) {
        TelegramHeader telegramHeader = null;
        int i = 0;
        TelegramIn telegramIn = new TelegramIn();
        try {
            telegramHeader = makeTelegramHeader(paramTelegramInputUserData);
            TelegramData telegramData = makeTelegramData(paramObject);
            telegramIn.setHeader(telegramHeader);
            telegramIn.setData(telegramData);
            i = TelegramUtil.getPacketSize(telegramIn) - 8;
            telegramIn.getHeader().setMsg_len(i);
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
        }
        return telegramIn;
    }

    public TelegramInList getTelegramInList(TelegramInputUserData paramTelegramInputUserData, List<?> paramList) {
        TelegramHeader telegramHeader = null;
        int i = 0;
        TelegramInList telegramInList = new TelegramInList();
        try {
            telegramHeader = makeTelegramHeader(paramTelegramInputUserData);
            TelegramDataList telegramDataList = makeTelegramDataList(paramList);
            telegramInList.setHeader(telegramHeader);
            telegramInList.setData(telegramDataList);
            i = TelegramUtil.getPacketSize(telegramInList) - 8;
            telegramInList.getHeader().setMsg_len(i);
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
        }
        return telegramInList;
    }

    public TelegramHeader makeTelegramHeader(TelegramInputUserData paramTelegramInputUserData) {
        TelegramHeader telegramHeader = new TelegramHeader();
        String str1 = null;
        try {
            str1 = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException unknownHostException) {
            ExceptionUtil.logPrintStackTrace(logger, unknownHostException);
        }
        if (str1 == null) {
            telegramHeader.setGid_sysname("MAAS");
        } else {
            telegramHeader.setGid_sysname(str1);
        }
        telegramHeader.setGid_yyyyymmdd(TelegramDateUtil.getYYYYMMDD());
        telegramHeader.setGid_hhmmss(TelegramDateUtil.getHHMMSS());
        telegramHeader.setGid_seq(GlobalSeq.getSeq());
        String str2 = System.getProperty("wlinstance");
        telegramHeader.setGid_pid(TelegramUtil.addLeftZeroPaddingByLength(str2, 5));
        telegramHeader.setGid_stat("00");
        telegramHeader.setTx_code(paramTelegramInputUserData.getTx_code());
        telegramHeader.setInst_no("MTI");
        telegramHeader.setSend_rspn_type("S");
        telegramHeader.setRspn_svc_code(paramTelegramInputUserData.getRspn_svc_code());
        telegramHeader.setOri_global_id(getMakeOriginalGid(telegramHeader));
        telegramHeader.setOri_send_time(TelegramDateUtil.getMicroTime());
        String str3 = System.getProperty("chnl_id");
        if (str3 == null) {
            telegramHeader.setChnl_id("WEB");
        } else {
            telegramHeader.setChnl_id(str3);
        }
        telegramHeader.setClient_ip_no(paramTelegramInputUserData.getClient_ip_no());
        telegramHeader.setClient_mac(paramTelegramInputUserData.getClient_mac());
        telegramHeader.setScrn_id(paramTelegramInputUserData.getScrn_id());
        telegramHeader.setScrn_lock_yn("N");
        telegramHeader.setOp_id(paramTelegramInputUserData.getOp_id());
        telegramHeader.setXa_begin_flag(0);
        telegramHeader.setSend_time(TelegramDateUtil.getMicroTime());
        telegramHeader.setRspn_time("");
        telegramHeader.setSync_type(paramTelegramInputUserData.getSync_type());
        if (paramTelegramInputUserData.getSync_type() != null
                && "A".equals(paramTelegramInputUserData.getSync_type())) {
            telegramHeader.setAsync_rspn_yn(paramTelegramInputUserData.getAsync_rspn_yn());
        } else {
            telegramHeader.setAsync_rspn_yn("");
        }
        telegramHeader.setCall_depth(0);
        telegramHeader.setMsg_count_no(0);
        telegramHeader.setTtl_use_flag(paramTelegramInputUserData.getTtl_use_flag());
        if (paramTelegramInputUserData.getTtl() != 0) {
            telegramHeader.setTtl_from_time(TelegramDateUtil.getHHMMSS());
            telegramHeader.setTtl(paramTelegramInputUserData.getTtl());
        } else {
            telegramHeader.setTtl_from_time("");
            telegramHeader.setTtl(0);
        }
        telegramHeader.setLong_msg_type(paramTelegramInputUserData.getLong_msg_type());
        telegramHeader.setErr_flag(0);
        telegramHeader.setErr_src("");
        telegramHeader.setErr_type("");
        telegramHeader.setErr_code("");
        if (paramTelegramInputUserData.getDst_inst_code() == null) {
            telegramHeader.setDst_inst_code("");
            telegramHeader.setFail_knd("");
            telegramHeader.setAp_host_name("");
            telegramHeader.setAp_caller_id("");
        } else {
            telegramHeader.setDst_inst_code(paramTelegramInputUserData.getDst_inst_code());
            telegramHeader.setFail_knd(paramTelegramInputUserData.getFail_knd());
            telegramHeader.setAp_host_name(paramTelegramInputUserData.getAp_host_name());
            telegramHeader.setAp_caller_id(paramTelegramInputUserData.getAp_caller_id());
        }
        telegramHeader.setInf_id("");
        telegramHeader.setLang_type(paramTelegramInputUserData.getLang_type());
        telegramHeader.setReserved("");
        return telegramHeader;
    }

    public TelegramDataList makeTelegramDataList(List<?> paramList) {
        TelegramDataList telegramDataList = new TelegramDataList();
        telegramDataList.setDataType("D");
        telegramDataList.setData(paramList);
        int i = 0;
        try {
            i = TelegramUtil.getPacketSize(telegramDataList) - 9;
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
        }
        telegramDataList.setLength(i);
        return telegramDataList;
    }

    public TelegramData makeTelegramData(Object paramObject) {
        TelegramData telegramData = new TelegramData();
        telegramData.setDataType("D");
        telegramData.setData(paramObject);
        int i = 0;
        try {
            i = TelegramUtil.getPacketSize(telegramData) - 9;
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
        }
        telegramData.setLength(i);
        return telegramData;
    }

    public String getMakeOriginalGid(TelegramHeader paramTelegramHeader) {
        String str = "";
        try {
            for (Field field : paramTelegramHeader.getClass().getDeclaredFields()) {
                if (field.getName().startsWith("gid_")) {
                    Object object = field.get(paramTelegramHeader);
                    str = str + new String(TelegramUtil.convertStringToBytes(object, field, "UTF-8"));
                }
            }
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
        }
        return str;
    }

    public TelegramOut getTelegramOutData(Object paramObject) {
        TelegramOut telegramOut = new TelegramOut();
        TelegramHeader telegramHeader = new TelegramHeader();
        TelegramMessage telegramMessage = new TelegramMessage();
        TelegramDataOut telegramDataOut = makeTelegramOut(paramObject);
        TelegramTail telegramTail = new TelegramTail();
        telegramOut.setHeader(telegramHeader);
        telegramOut.setMessage(telegramMessage);
        telegramOut.setData(telegramDataOut);
        telegramOut.setTail(telegramTail);
        return telegramOut;
    }

    public TelegramOut getTelegramOutData(TelegramHeader paramTelegramHeader, TelegramMessage paramTelegramMessage,
            Object paramObject) {
        TelegramOut telegramOut = new TelegramOut();
        TelegramDataOut telegramDataOut = makeTelegramOut(paramObject);
        TelegramTail telegramTail = new TelegramTail();
        telegramOut.setHeader(paramTelegramHeader);
        telegramOut.setMessage(paramTelegramMessage);
        telegramOut.setData(telegramDataOut);
        telegramOut.setTail(telegramTail);
        return telegramOut;
    }

    public TelegramOutNoData getTelegramOutDataNoData() {
        TelegramOutNoData telegramOutNoData = new TelegramOutNoData();
        TelegramHeader telegramHeader = new TelegramHeader();
        TelegramMessage telegramMessage = new TelegramMessage();
        TelegramTail telegramTail = new TelegramTail();
        telegramOutNoData.setHeader(telegramHeader);
        telegramOutNoData.setMessage(telegramMessage);
        telegramOutNoData.setTail(telegramTail);
        return telegramOutNoData;
    }

    public TelegramOutList getTelegramOutDataList(List<Object> paramList) {
        TelegramOutList telegramOutList = new TelegramOutList();
        TelegramHeader telegramHeader = new TelegramHeader();
        TelegramMessage telegramMessage = new TelegramMessage();
        TelegramDataOutList telegramDataOutList = makeTelegramOutList(paramList);
        TelegramTail telegramTail = new TelegramTail();
        telegramOutList.setHeader(telegramHeader);
        telegramOutList.setMessage(telegramMessage);
        telegramOutList.setData(telegramDataOutList);
        telegramOutList.setTail(telegramTail);
        return telegramOutList;
    }

    public TelegramDataOut makeTelegramOut(Object paramObject) {
        TelegramDataOut telegramDataOut = new TelegramDataOut();
        telegramDataOut.setData(paramObject);
        return telegramDataOut;
    }

    public TelegramDataOutList makeTelegramOutList(Object paramObject) {
        TelegramDataOutList telegramDataOutList = new TelegramDataOutList();
        ArrayList arrayList = new ArrayList();
        telegramDataOutList.setData(arrayList);
        return telegramDataOutList;
    }
}
