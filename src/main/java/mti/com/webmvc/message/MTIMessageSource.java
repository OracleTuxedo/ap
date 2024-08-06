package mti.com.webmvc.message;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.util.InterfaceTelegram;
import mti.com.telegram.util.TelegramUtil;
import mti.com.telegram.vo.TelegramInputUserData;
import mti.com.telegram.vo.TelegramMessage;
import mti.com.telegram.vo.TelegramOutputUserData;
import mti.com.utility.ExceptionUtil;
import mti.com.webmvc.message.MTIMessageSource;
import mti.com.webmvc.message.vo.CodeEntry;
import mti.com.webmvc.message.vo.SAZ03V798RInVO;
import mti.com.webmvc.message.vo.SAZ03V798ROutSubVO;
import mti.com.webmvc.message.vo.SAZ03V798ROutVO;
import mti.com.webmvc.message.vo.SAZ03V799RInVo;
import mti.com.webmvc.message.vo.SAZ03V799ROutSubVO;
import mti.com.webmvc.message.vo.SAZ03V799ROutVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;

public class MTIMessageSource implements DisposableBean {
    private final Map<Locale, CachingBizCdMap> cachedCodeBundles = new ConcurrentHashMap<>();

    private final Map<Locale, CachingMsgGroupMap> cachedMessageBundles = new ConcurrentHashMap<>();

    private boolean wasStarted = false;

    private Locale defaultLocale;

    private String strLocale;

    private static final String MSGNORMAL = "N";

    private static final Logger logger = LogManager.getLogger(MTIMessageSource.class);

    public String getStrLocale() {
        return this.strLocale;
    }

    public void setWasStarted() {
        this.wasStarted = true;
    }

    public void setStrLocale(String paramString) {
        this.strLocale = paramString;
        this.defaultLocale = new Locale(paramString);
    }

    public synchronized String resolveMessageWithArguments(String paramString1, Locale paramLocale,
            String[] paramArrayOfString, String paramString2) {
        String str = paramString2;
        if (!this.wasStarted)
            try {
                refreshAll();
                setWasStarted();
            } catch (Exception exception) {
                logger.error(exception);
            }
        if (paramLocale == null)
            paramLocale = this.defaultLocale;
        try {
            MessageFormat messageFormat = getMessageFormatFromMessage(paramLocale, paramString1);
            if (messageFormat == null)
                return paramString2;
            str = messageFormat.toPattern();
            for (byte b = 0; paramArrayOfString != null && b < paramArrayOfString.length; b++) {
                StringBuffer stringBuffer = new StringBuffer();
                int i = str.indexOf("[%s]");
                if (i < 0)
                    break;
                String str1 = str.substring(0, i);
                stringBuffer.append(str1);
                stringBuffer.append(paramArrayOfString[b]);
                String str2 = str.substring(i + 4);
                stringBuffer.append(str2);
                str = stringBuffer.toString();
            }
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
        }
        return str;
    }

    public synchronized MessageFormat resolveCode(String paramString, Locale paramLocale) {
        if (!this.wasStarted)
            try {
                refreshAll();
                setWasStarted();
            } catch (Exception exception) {
                logger.error(exception);
            }
        if (paramLocale == null)
            paramLocale = this.defaultLocale;
        MessageFormat messageFormat = null;
        try {
            if (paramString != null && paramString.length() > 1) {
                String str = paramString.substring(0, 2);
                if ("C@".equals(str)) {
                    paramString = paramString.substring(2);
                    messageFormat = getMessageFormatFromCode(paramLocale, paramString);
                }
                if ("M@".equals(str)) {
                    paramString = paramString.substring(2);
                    messageFormat = getMessageFormatFromMessage(paramLocale, paramString);
                }
            }
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
        }
        return messageFormat;
    }

    public synchronized String resolveCodeWithoutArguments(String paramString, Locale paramLocale) {
        if (!this.wasStarted)
            try {
                refreshAll();
                setWasStarted();
            } catch (Exception exception) {
                logger.error(exception);
            }
        String str = null;
        if (paramLocale == null)
            paramLocale = this.defaultLocale;
        MessageFormat messageFormat = null;
        try {
            if (paramString != null && paramString.length() > 1) {
                String str1 = paramString.substring(0, 2);
                if ("C@".equals(str1)) {
                    paramString = paramString.substring(2);
                    messageFormat = getMessageFormatFromCode(paramLocale, paramString);
                }
                if ("M@".equals(str1)) {
                    paramString = paramString.substring(2);
                    messageFormat = getMessageFormatFromMessage(paramLocale, paramString);
                }
                if (messageFormat != null)
                    str = messageFormat.toPattern();
            }
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
        }
        return str;
    }

    public synchronized ArrayList<CodeEntry> resolveCodeList(Locale paramLocale, String paramString) {
        if (!this.wasStarted)
            try {
                refreshAll();
                setWasStarted();
            } catch (Exception exception) {
                logger.error(exception);
            }
        if (paramLocale == null)
            paramLocale = this.defaultLocale;
        ArrayList<CodeEntry> arrayList = new ArrayList();
        CachingBizCdMap cachingBizCdMap = this.cachedCodeBundles.get(paramLocale);
        if (cachingBizCdMap == null)
            return null;
        Map<String, CachingCodeGroupMap> map = cachingBizCdMap.getBizMap();
        if (map == null)
            return null;
        Set<String> set = map.keySet();
        Iterator<String> iterator = set.iterator();
        boolean bool = false;
        while (iterator.hasNext()) {
            String str = iterator.next();
            if (paramString.contains(str)) {
                int i = paramString.indexOf(str);
                String str1 = paramString.substring(i + str.length());
                CachingCodeGroupMap cachingCodeGroupMap = map.get(str);
                Map<String, CachingCodeGroup> map1 = cachingCodeGroupMap.getGroupMap();
                Set<String> set1 = map1.keySet();
                for (String str2 : set1) {
                    if (stringEquals(str1, str2)) {
                        CachingCodeGroup cachingCodeGroup = map1.get(str2);
                        ArrayList<CachingCodeData> arrayList1 = cachingCodeGroup.getList();
                        for (byte b = 0; arrayList1 != null && b < arrayList1.size(); b++) {
                            CachingCodeData cachingCodeData = arrayList1.get(b);
                            CodeEntry codeEntry = new CodeEntry();
                            codeEntry.setKey(cachingCodeData.getDtl_cd_id());
                            codeEntry.setContent(cachingCodeData.getDtl_cd_name().toPattern());
                            codeEntry.setExt1(cachingCodeData.getStringFormat(cachingCodeData.getClss_info_val1()));
                            codeEntry.setExt2(cachingCodeData.getStringFormat(cachingCodeData.getClss_info_val2()));
                            codeEntry.setExt3(cachingCodeData.getStringFormat(cachingCodeData.getClss_info_val3()));
                            codeEntry.setExt4(cachingCodeData.getStringFormat(cachingCodeData.getClss_info_val4()));
                            codeEntry.setExt5(cachingCodeData.getStringFormat(cachingCodeData.getClss_info_val5()));
                            codeEntry.setExt6(cachingCodeData.getStringFormat(cachingCodeData.getClss_info_val6()));
                            arrayList.add(codeEntry);
                        }
                        bool = true;
                        break;
                    }
                }
            }
            if (bool)
                break;
        }
        return arrayList;
    }

    public MessageFormat getMessageFormatFromCode(Locale paramLocale, String paramString) {
        MessageFormat messageFormat = null;
        CachingBizCdMap cachingBizCdMap = this.cachedCodeBundles.get(paramLocale);
        if (cachingBizCdMap == null)
            return null;
        Map<String, CachingCodeGroupMap> map = cachingBizCdMap.getBizMap();
        if (map == null)
            return null;
        Set<String> set = map.keySet();
        for (String str : set) {
            if (paramString.contains(str)) {
                int i = paramString.indexOf(str);
                String str1 = paramString.substring(i + str.length());
                CachingCodeGroupMap cachingCodeGroupMap = map.get(str);
                Map<String, CachingCodeGroup> map1 = cachingCodeGroupMap.getGroupMap();
                Set<String> set1 = map1.keySet();
                for (String str2 : set1) {
                    if (str1.contains(str2)) {
                        int j = str1.indexOf(str2);
                        String str3 = str1.substring(j + str2.length());
                        CachingCodeGroup cachingCodeGroup = map1.get(str2);
                        ArrayList<CachingCodeData> arrayList = cachingCodeGroup.getList();
                        for (byte b = 0; arrayList != null && b < arrayList.size(); b++) {
                            CachingCodeData cachingCodeData = arrayList.get(b);
                            if (stringEquals(str3, cachingCodeData.getDtl_cd_id())) {
                                messageFormat = cachingCodeData.getDtl_cd_name();
                                break;
                            }
                        }
                    }
                    if (messageFormat != null)
                        break;
                }
            }
            if (messageFormat != null)
                break;
        }
        return messageFormat;
    }

    public synchronized MessageFormat getExtMessageFormatFromCode(Locale paramLocale, String paramString,
            int paramInt) {
        MessageFormat messageFormat = null;
        if (!this.wasStarted)
            try {
                refreshAll();
                setWasStarted();
            } catch (Exception exception) {
                logger.error(exception);
            }
        if (paramLocale == null)
            paramLocale = this.defaultLocale;
        CachingBizCdMap cachingBizCdMap = this.cachedCodeBundles.get(paramLocale);
        if (cachingBizCdMap == null)
            return null;
        Map<String, CachingCodeGroupMap> map = cachingBizCdMap.getBizMap();
        if (map == null)
            return null;
        Set<String> set = map.keySet();
        for (String str : set) {
            if (paramString.contains(str)) {
                int i = paramString.indexOf(str);
                String str1 = paramString.substring(i + str.length());
                CachingCodeGroupMap cachingCodeGroupMap = map.get(str);
                Map<String, CachingCodeGroup> map1 = cachingCodeGroupMap.getGroupMap();
                Set<String> set1 = map1.keySet();
                for (String str2 : set1) {
                    if (str1.contains(str2)) {
                        int j = str1.indexOf(str2);
                        String str3 = str1.substring(j + str2.length());
                        CachingCodeGroup cachingCodeGroup = map1.get(str2);
                        ArrayList<CachingCodeData> arrayList = cachingCodeGroup.getList();
                        for (byte b = 0; arrayList != null && b < arrayList.size(); b++) {
                            CachingCodeData cachingCodeData = arrayList.get(b);
                            if (stringEquals(str3, cachingCodeData.getDtl_cd_id())) {
                                messageFormat = getExtData(cachingCodeData, paramInt);
                                break;
                            }
                        }
                    }
                    if (messageFormat != null)
                        break;
                }
            }
            if (messageFormat != null)
                break;
        }
        return messageFormat;
    }

    protected MessageFormat getExtData(CachingCodeData paramCachingCodeData, int paramInt) {
        MessageFormat messageFormat = null;
        switch (paramInt) {
            case 1:
                messageFormat = paramCachingCodeData.getClss_info_val1();
                break;
            case 2:
                messageFormat = paramCachingCodeData.getClss_info_val2();
                break;
            case 3:
                messageFormat = paramCachingCodeData.getClss_info_val3();
                break;
            case 4:
                messageFormat = paramCachingCodeData.getClss_info_val4();
                break;
            case 5:
                messageFormat = paramCachingCodeData.getClss_info_val5();
                break;
            case 6:
                messageFormat = paramCachingCodeData.getClss_info_val6();
                break;
        }
        return messageFormat;
    }

    public MessageFormat getMessageFormatFromMessage(Locale paramLocale, String paramString) throws Exception {
        MessageFormat messageFormat = null;
        CachingMsgGroupMap cachingMsgGroupMap = this.cachedMessageBundles.get(paramLocale);
        if (cachingMsgGroupMap == null)
            return null;
        Map<String, CachingMsgGroup> map = cachingMsgGroupMap.getGrpMap();
        if (map == null)
            return null;
        Set<String> set = map.keySet();
        // str = "";
        for (String str : set) {
            if (paramString.contains(str)) {
                int i = paramString.indexOf(str);
                String str1 = paramString.substring(i + str.length());
                CachingMsgGroup cachingMsgGroup = map.get(str);
                ArrayList<CachingMsg> arrayList = cachingMsgGroup.getList();
                for (byte b = 0; arrayList != null && b < arrayList.size(); b++) {
                    CachingMsg cachingMsg = arrayList.get(b);
                    String str2 = cachingMsg.getMsg_id();
                    if (stringEquals(str1, str2)) {
                        messageFormat = cachingMsg.getMsg_nm();
                        break;
                    }
                }
            }
            if (messageFormat != null)
                break;
        }
        return messageFormat;
    }

    public void destroy() throws Exception {
        if (this.cachedCodeBundles != null)
            this.cachedCodeBundles.clear();
        if (this.cachedMessageBundles != null)
            this.cachedMessageBundles.clear();
    }

    public synchronized void refreshAll() throws Exception {
        refreshCode();
        refreshMessage();
    }

    public synchronized void refreshCode() throws Exception {
        TelegramInputUserData telegramInputUserData = new TelegramInputUserData();
        telegramInputUserData.setTx_code("SAZ03V798R");
        String str1 = TelegramUtil.getIpAddress();
        String str2 = TelegramUtil.getMacAddress();
        telegramInputUserData.setClient_ip_no(str1);
        telegramInputUserData.setClient_mac(str2);
        telegramInputUserData.setScrn_id("WAZ0217000");
        telegramInputUserData.setOp_id("2017900061");
        telegramInputUserData.setSync_type("A");
        telegramInputUserData.setRspn_svc_code("");
        telegramInputUserData.setAsync_rspn_yn("0");
        telegramInputUserData.setTtl_use_flag(0);
        telegramInputUserData.setLang_type("EN");
        SAZ03V798RInVO sAZ03V798RInVO = new SAZ03V798RInVO();
        SAZ03V798ROutVO sAZ03V798ROutVO = new SAZ03V798ROutVO();
        TelegramOutputUserData telegramOutputUserData = null;
        try {
            telegramOutputUserData = InterfaceTelegram.interfaceTuxedo(telegramInputUserData, sAZ03V798RInVO,
                    sAZ03V798ROutVO, false);
            if (telegramOutputUserData != null) {
                sAZ03V798ROutVO = (SAZ03V798ROutVO) telegramOutputUserData.getOutput();
                TelegramMessage telegramMessage = telegramOutputUserData.getMessage();
                String str = telegramMessage.getKind();
                if (!"N".equals(str)) {
                    errorLog("Message Info : ");
                    errorLog(telegramMessage.toString());
                } else if (this.cachedCodeBundles != null) {
                    this.cachedCodeBundles.clear();
                }
            }
        } catch (TelegramNestedRuntimeException telegramNestedRuntimeException) {
            logger.error(telegramNestedRuntimeException);
            throw telegramNestedRuntimeException;
        } catch (Exception exception) {
            logger.error(exception);
            throw exception;
        }
        if (sAZ03V798ROutVO != null) {
            List<SAZ03V798ROutSubVO> list = sAZ03V798ROutVO.getList();
            loadCode(list);
        }
    }

    public synchronized void refreshMessage() throws Exception {
        TelegramInputUserData telegramInputUserData = new TelegramInputUserData();
        telegramInputUserData.setTx_code("SAZ03V799R");
        String str1 = TelegramUtil.getIpAddress();
        String str2 = TelegramUtil.getMacAddress();
        telegramInputUserData.setClient_ip_no(str1);
        telegramInputUserData.setClient_mac(str2);
        telegramInputUserData.setScrn_id("WAZ0217000");
        telegramInputUserData.setOp_id("2017900061");
        telegramInputUserData.setSync_type("A");
        telegramInputUserData.setRspn_svc_code("");
        telegramInputUserData.setAsync_rspn_yn("0");
        telegramInputUserData.setTtl_use_flag(0);
        telegramInputUserData.setLang_type("EN");
        SAZ03V799RInVo sAZ03V799RInVo = new SAZ03V799RInVo();
        SAZ03V799ROutVO sAZ03V799ROutVO = new SAZ03V799ROutVO();
        TelegramOutputUserData telegramOutputUserData = null;
        try {
            telegramOutputUserData = InterfaceTelegram.interfaceTuxedo(telegramInputUserData, sAZ03V799RInVo,
                    sAZ03V799ROutVO, false);
            if (telegramOutputUserData != null) {
                sAZ03V799ROutVO = (SAZ03V799ROutVO) telegramOutputUserData.getOutput();
                TelegramMessage telegramMessage = telegramOutputUserData.getMessage();
                String str = telegramMessage.getKind();
                if (!"N".equals(str)) {
                    errorLog("Message Info : ");
                    errorLog(telegramMessage.toString());
                } else if (this.cachedMessageBundles != null) {
                    this.cachedMessageBundles.clear();
                }
            }
        } catch (TelegramNestedRuntimeException telegramNestedRuntimeException) {
            logger.error(telegramNestedRuntimeException);
            throw telegramNestedRuntimeException;
        } catch (Exception exception) {
            logger.error(exception);
            throw exception;
        }
        if (sAZ03V799ROutVO != null) {
            List<SAZ03V799ROutSubVO> list = sAZ03V799ROutVO.getList();
            loadMessage(list);
        }
    }

    public void loadCode(List<SAZ03V798ROutSubVO> paramList) {
        for (byte b = 0; paramList != null && b < paramList.size(); b++)
            insertCodeVoToMapBundle(paramList.get(b));
    }

    public void loadMessage(List<SAZ03V799ROutSubVO> paramList) {
        for (byte b = 0; paramList != null && b < paramList.size(); b++)
            insertMsgVoToMapBundle(paramList.get(b));
    }

    public void insertCodeVoToMapBundle(SAZ03V798ROutSubVO paramSAZ03V798ROutSubVO) {
        String str1 = paramSAZ03V798ROutSubVO.getDtl_cd_id();
        String str2 = paramSAZ03V798ROutSubVO.getBiz_ctgo_cd();
        String str3 = paramSAZ03V798ROutSubVO.getGrup_cd_id();
        String str4 = paramSAZ03V798ROutSubVO.getLang_clcd();
        String str5 = paramSAZ03V798ROutSubVO.getDtl_cd_nm();
        Locale locale = new Locale(str4);
        CachingBizCdMap cachingBizCdMap = this.cachedCodeBundles.get(locale);
        if (cachingBizCdMap == null) {
            cachingBizCdMap = new CachingBizCdMap(locale, str2);
            CachingCodeGroupMap cachingCodeGroupMap = new CachingCodeGroupMap(str2);
            CachingCodeGroup cachingCodeGroup = new CachingCodeGroup(str3);
            ArrayList<CachingCodeData> arrayList = new ArrayList();
            CachingCodeData cachingCodeData = new CachingCodeData(str1, new MessageFormat(str5, locale));
            cachingCodeData.setClss_info_val1(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val1(), locale));
            cachingCodeData.setClss_info_val2(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val2(), locale));
            cachingCodeData.setClss_info_val3(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val3(), locale));
            cachingCodeData.setClss_info_val4(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val4(), locale));
            cachingCodeData.setClss_info_val5(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val5(), locale));
            cachingCodeData.setClss_info_val6(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val6(), locale));
            arrayList.add(cachingCodeData);
            cachingCodeGroup.setList(arrayList);
            cachingCodeGroupMap.putCachingCodeGroup(str3, cachingCodeGroup);
            cachingBizCdMap.putCachingCodeGroupMap(str2, cachingCodeGroupMap);
            this.cachedCodeBundles.put(locale, cachingBizCdMap);
        } else {
            CachingCodeGroupMap cachingCodeGroupMap = cachingBizCdMap.getCachingCodeGroupMap(str2);
            if (cachingCodeGroupMap == null) {
                cachingCodeGroupMap = new CachingCodeGroupMap(str2);
                CachingCodeGroup cachingCodeGroup = new CachingCodeGroup(str3);
                ArrayList<CachingCodeData> arrayList = new ArrayList();
                CachingCodeData cachingCodeData = new CachingCodeData(str1, new MessageFormat(str5, locale));
                cachingCodeData
                        .setClss_info_val1(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val1(), locale));
                cachingCodeData
                        .setClss_info_val2(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val2(), locale));
                cachingCodeData
                        .setClss_info_val3(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val3(), locale));
                cachingCodeData
                        .setClss_info_val4(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val4(), locale));
                cachingCodeData
                        .setClss_info_val5(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val5(), locale));
                cachingCodeData
                        .setClss_info_val6(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val6(), locale));
                arrayList.add(cachingCodeData);
                cachingCodeGroup.setList(arrayList);
                cachingCodeGroupMap.putCachingCodeGroup(str3, cachingCodeGroup);
                cachingBizCdMap.putCachingCodeGroupMap(str2, cachingCodeGroupMap);
            } else {
                CachingCodeGroup cachingCodeGroup = cachingCodeGroupMap.getCachingCodeGroup(str3);
                if (cachingCodeGroup == null) {
                    cachingCodeGroup = new CachingCodeGroup(str3);
                    ArrayList<CachingCodeData> arrayList = cachingCodeGroup.getList();
                    CachingCodeData cachingCodeData = new CachingCodeData(str1, new MessageFormat(str5, locale));
                    cachingCodeData
                            .setClss_info_val1(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val1(), locale));
                    cachingCodeData
                            .setClss_info_val2(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val2(), locale));
                    cachingCodeData
                            .setClss_info_val3(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val3(), locale));
                    cachingCodeData
                            .setClss_info_val4(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val4(), locale));
                    cachingCodeData
                            .setClss_info_val5(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val5(), locale));
                    cachingCodeData
                            .setClss_info_val6(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val6(), locale));
                    arrayList.add(cachingCodeData);
                    cachingCodeGroup.setList(arrayList);
                    cachingCodeGroupMap.putCachingCodeGroup(str3, cachingCodeGroup);
                } else {
                    ArrayList<CachingCodeData> arrayList = cachingCodeGroup.getList();
                    CachingCodeData cachingCodeData = new CachingCodeData(str1, new MessageFormat(str5, locale));
                    cachingCodeData
                            .setClss_info_val1(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val1(), locale));
                    cachingCodeData
                            .setClss_info_val2(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val2(), locale));
                    cachingCodeData
                            .setClss_info_val3(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val3(), locale));
                    cachingCodeData
                            .setClss_info_val4(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val4(), locale));
                    cachingCodeData
                            .setClss_info_val5(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val5(), locale));
                    cachingCodeData
                            .setClss_info_val6(new MessageFormat(paramSAZ03V798ROutSubVO.getClss_info_val6(), locale));
                    arrayList.add(cachingCodeData);
                }
            }
        }
    }

    public void insertMsgVoToMapBundle(SAZ03V799ROutSubVO paramSAZ03V799ROutSubVO) {
        String str1 = paramSAZ03V799ROutSubVO.getMsg_clcd();
        String str2 = paramSAZ03V799ROutSubVO.getMsg_id();
        String str3 = paramSAZ03V799ROutSubVO.getLang_clcd();
        String str4 = paramSAZ03V799ROutSubVO.getMsg_nm();
        Locale locale = new Locale(str3);
        CachingMsgGroupMap cachingMsgGroupMap = this.cachedMessageBundles.get(locale);
        if (cachingMsgGroupMap == null) {
            cachingMsgGroupMap = new CachingMsgGroupMap(locale);
            CachingMsgGroup cachingMsgGroup = new CachingMsgGroup(str1);
            ArrayList<CachingMsg> arrayList = cachingMsgGroup.getList();
            CachingMsg cachingMsg = new CachingMsg(str2, new MessageFormat(str4, locale));
            arrayList.add(cachingMsg);
            cachingMsgGroup.setList(arrayList);
            cachingMsgGroupMap.putCachingMsgGroupMap(str1, cachingMsgGroup);
            this.cachedMessageBundles.put(locale, cachingMsgGroupMap);
        } else {
            CachingMsgGroup cachingMsgGroup = cachingMsgGroupMap.getCachingMsgGroupMap(str1);
            if (cachingMsgGroup == null) {
                cachingMsgGroup = new CachingMsgGroup(str1);
                ArrayList<CachingMsg> arrayList = cachingMsgGroup.getList();
                CachingMsg cachingMsg = new CachingMsg(str2, new MessageFormat(str4, locale));
                arrayList.add(cachingMsg);
                cachingMsgGroupMap.putCachingMsgGroupMap(str1, cachingMsgGroup);
            } else {
                ArrayList<CachingMsg> arrayList = cachingMsgGroup.getList();
                CachingMsg cachingMsg = new CachingMsg(str2, new MessageFormat(str4, locale));
                arrayList.add(cachingMsg);
            }
        }
    }

    private boolean stringEquals(String paramString1, String paramString2) {
        return (paramString1 == null) ? false : ((paramString2 == null) ? false : paramString1.equals(paramString2));
    }

    public static void main(String[] paramArrayOfString) {
        String[] arrayOfString = { "aa", "bb" };
        String str = "first [%s] , second [%s]";
        for (byte b = 0; arrayOfString != null && b < arrayOfString.length; b++) {
            StringBuffer stringBuffer = new StringBuffer();
            int i = str.indexOf("[%s]");
            if (i < 0)
                break;
            String str1 = str.substring(0, i);
            stringBuffer.append(str1);
            stringBuffer.append(arrayOfString[b]);
            String str2 = str.substring(i + 4);
            stringBuffer.append(str2);
            str = stringBuffer.toString();
        }
        System.out.println("Resut : " + str);
    }

    private void infoLog(String paramString) {
        if (logger.isInfoEnabled())
            logger.info(paramString);
    }

    private void warnLog(String paramString) {
        if (logger.isWarnEnabled())
            logger.warn(paramString);
    }

    private void debugLog(String paramString) {
        if (logger.isDebugEnabled())
            logger.debug(paramString);
    }

    private void errorLog(String paramString) {
        if (logger.isErrorEnabled())
            logger.error(paramString);
    }

    private class CachingCodeData {
        String dtl_cd_id;

        MessageFormat dtl_cd_name;

        MessageFormat clss_info_val1;

        MessageFormat clss_info_val2;

        MessageFormat clss_info_val3;

        MessageFormat clss_info_val4;

        MessageFormat clss_info_val5;

        MessageFormat clss_info_val6;

        public CachingCodeData(String param1String, MessageFormat param1MessageFormat) {
            this.dtl_cd_id = param1String;
            this.dtl_cd_name = param1MessageFormat;
        }

        public String getDtl_cd_id() {
            return this.dtl_cd_id;
        }

        public void setDtl_cd_id(String param1String) {
            this.dtl_cd_id = param1String;
        }

        public MessageFormat getDtl_cd_name() {
            return this.dtl_cd_name;
        }

        public void setDtl_cd_name(MessageFormat param1MessageFormat) {
            this.dtl_cd_name = param1MessageFormat;
        }

        public MessageFormat getClss_info_val1() {
            return this.clss_info_val1;
        }

        public void setClss_info_val1(MessageFormat param1MessageFormat) {
            this.clss_info_val1 = param1MessageFormat;
        }

        public MessageFormat getClss_info_val2() {
            return this.clss_info_val2;
        }

        public void setClss_info_val2(MessageFormat param1MessageFormat) {
            this.clss_info_val2 = param1MessageFormat;
        }

        public MessageFormat getClss_info_val3() {
            return this.clss_info_val3;
        }

        public void setClss_info_val3(MessageFormat param1MessageFormat) {
            this.clss_info_val3 = param1MessageFormat;
        }

        public MessageFormat getClss_info_val4() {
            return this.clss_info_val4;
        }

        public void setClss_info_val4(MessageFormat param1MessageFormat) {
            this.clss_info_val4 = param1MessageFormat;
        }

        public MessageFormat getClss_info_val5() {
            return this.clss_info_val5;
        }

        public void setClss_info_val5(MessageFormat param1MessageFormat) {
            this.clss_info_val5 = param1MessageFormat;
        }

        public MessageFormat getClss_info_val6() {
            return this.clss_info_val6;
        }

        public void setClss_info_val6(MessageFormat param1MessageFormat) {
            this.clss_info_val6 = param1MessageFormat;
        }

        public String toString() {
            return "CachingCodeData [dtl_cd_id=" + this.dtl_cd_id + ", dtl_cd_name=" + getStringFormat(this.dtl_cd_name)
                    + ", clss_info_val1=" + getStringFormat(this.clss_info_val1) + ", clss_info_val2="
                    + getStringFormat(this.clss_info_val2) + ", clss_info_val3=" + getStringFormat(this.clss_info_val3)
                    + ", clss_info_val4=" + getStringFormat(this.clss_info_val4) + ", clss_info_val5="
                    + getStringFormat(this.clss_info_val5) + ", clss_info_val6=" + getStringFormat(this.clss_info_val6)
                    + "]";
        }

        public String getStringFormat(MessageFormat param1MessageFormat) {
            return (param1MessageFormat == null) ? "null" : param1MessageFormat.toPattern();
        }
    }

    private class CachingCodeGroup {
        String group_cd_id;

        ArrayList<MTIMessageSource.CachingCodeData> list;

        public CachingCodeGroup(String param1String) {
            this.group_cd_id = param1String;
            this.list = new ArrayList<>();
        }

        public String getGroup_cd_id() {
            return this.group_cd_id;
        }

        public void setGroup_cd_id(String param1String) {
            this.group_cd_id = param1String;
        }

        public ArrayList<MTIMessageSource.CachingCodeData> getList() {
            return this.list;
        }

        public void setList(ArrayList<MTIMessageSource.CachingCodeData> param1ArrayList) {
            this.list = param1ArrayList;
        }

        public void insertCachingCodeData(MTIMessageSource.CachingCodeData param1CachingCodeData) {
            this.list.add(param1CachingCodeData);
        }
    }

    private class CachingCodeGroupMap {
        String biz_ctgo_cd;

        Map<String, MTIMessageSource.CachingCodeGroup> groupMap;

        public CachingCodeGroupMap(String param1String) {
            this.biz_ctgo_cd = param1String;
            this.groupMap = new HashMap<>();
        }

        public String getBiz_ctgo_cd() {
            return this.biz_ctgo_cd;
        }

        public void setBiz_ctgo_cd(String param1String) {
            this.biz_ctgo_cd = param1String;
        }

        public Map<String, MTIMessageSource.CachingCodeGroup> getGroupMap() {
            return this.groupMap;
        }

        public void setGroupMap(Map<String, MTIMessageSource.CachingCodeGroup> param1Map) {
            this.groupMap = param1Map;
        }

        public MTIMessageSource.CachingCodeGroup getCachingCodeGroup(String param1String) {
            return this.groupMap.get(param1String);
        }

        public void putCachingCodeGroup(String param1String,
                MTIMessageSource.CachingCodeGroup param1CachingCodeGroup) {
            this.groupMap.put(param1String, param1CachingCodeGroup);
        }
    }

    private class CachingBizCdMap {
        Map<String, MTIMessageSource.CachingCodeGroupMap> bizMap = new HashMap<>();

        Locale locale;

        public CachingBizCdMap(Locale param1Locale, String param1String) {
            this.locale = param1Locale;
        }

        public Locale getLocale() {
            return this.locale;
        }

        public void setLocale(Locale param1Locale) {
            this.locale = param1Locale;
        }

        public Map<String, MTIMessageSource.CachingCodeGroupMap> getBizMap() {
            return this.bizMap;
        }

        public void setBizMap(Map<String, MTIMessageSource.CachingCodeGroupMap> param1Map) {
            this.bizMap = param1Map;
        }

        public void putCachingCodeGroupMap(String param1String,
                MTIMessageSource.CachingCodeGroupMap param1CachingCodeGroupMap) {
            this.bizMap.put(param1String, param1CachingCodeGroupMap);
        }

        public MTIMessageSource.CachingCodeGroupMap getCachingCodeGroupMap(String param1String) {
            return this.bizMap.get(param1String);
        }
    }

    private class CachingMsg {
        String msg_id;

        MessageFormat msg_nm;

        public CachingMsg(String param1String, MessageFormat param1MessageFormat) {
            this.msg_id = param1String;
            this.msg_nm = param1MessageFormat;
        }

        public String getMsg_id() {
            return this.msg_id;
        }

        public void setMsg_id(String param1String) {
            this.msg_id = param1String;
        }

        public MessageFormat getMsg_nm() {
            return this.msg_nm;
        }

        public void setMsg_nm(MessageFormat param1MessageFormat) {
            this.msg_nm = param1MessageFormat;
        }

        public String toString() {
            return "CachingMsg [msg_id=" + this.msg_id + ", msg_nm="
                    + ((this.msg_nm == null) ? "null" : this.msg_nm.toPattern()) + "]";
        }
    }

    private class CachingMsgGroup {
        String msg_clcd;

        ArrayList<MTIMessageSource.CachingMsg> list;

        public CachingMsgGroup(String param1String) {
            this.msg_clcd = param1String;
            this.list = new ArrayList<>();
        }

        public String getMsg_clcd() {
            return this.msg_clcd;
        }

        public void setMsg_clcd(String param1String) {
            this.msg_clcd = param1String;
        }

        public ArrayList<MTIMessageSource.CachingMsg> getList() {
            return this.list;
        }

        public void setList(ArrayList<MTIMessageSource.CachingMsg> param1ArrayList) {
            this.list = param1ArrayList;
        }

        public void insertCachingMsg(MTIMessageSource.CachingMsg param1CachingMsg) {
            this.list.add(param1CachingMsg);
        }
    }

    private class CachingMsgGroupMap {
        Map<String, MTIMessageSource.CachingMsgGroup> grpMap;

        Locale locale;

        public CachingMsgGroupMap(Locale param1Locale) {
            this.locale = param1Locale;
            this.grpMap = new HashMap<>();
        }

        public Map<String, MTIMessageSource.CachingMsgGroup> getGrpMap() {
            return this.grpMap;
        }

        public void setGrpMap(Map<String, MTIMessageSource.CachingMsgGroup> param1Map) {
            this.grpMap = param1Map;
        }

        public Locale getLocale() {
            return this.locale;
        }

        public void setLocale(Locale param1Locale) {
            this.locale = param1Locale;
        }

        public void putCachingMsgGroupMap(String param1String,
                MTIMessageSource.CachingMsgGroup param1CachingMsgGroup) {
            this.grpMap.put(param1String, param1CachingMsgGroup);
        }

        public MTIMessageSource.CachingMsgGroup getCachingMsgGroupMap(String param1String) {
            return this.grpMap.get(param1String);
        }
    }
}
