package mti.com.telegram.util;

import java.util.List;
import mti.com.telegram.exception.TelegramNestedRuntimeException;
import mti.com.telegram.mapping.ByteDecoder;
import mti.com.telegram.mapping.ByteEncoder;
import mti.com.telegram.vo.TelegramHeader;
import mti.com.telegram.vo.TelegramIn;
import mti.com.telegram.vo.TelegramInList;
import mti.com.telegram.vo.TelegramInputUserData;
import mti.com.telegram.vo.TelegramMessage;
import mti.com.telegram.vo.TelegramOut;
import mti.com.telegram.vo.TelegramOutList;
import mti.com.telegram.vo.TelegramOutNoData;
import mti.com.telegram.vo.TelegramOutputUserData;
import mti.com.telegram.vo.TelegramTail;
import mti.com.utility.ExceptionUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InterfaceTelegram {
    private static final Logger logger = LogManager.getLogger(InterfaceTelegram.class);

    public static TelegramOutputUserData interfaceTuxedo(TelegramInputUserData paramTelegramInputUserData,
            Object paramObject1, Object paramObject2) throws Exception {
        boolean bool = true;
        Object object = null;
        TelegramBuilder telegramBuilder = new TelegramBuilder();
        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn telegramIn = telegramBuilder.getTelegramIn(paramTelegramInputUserData, paramObject1);
        byte[] arrayOfByte1 = byteEncoder.convertObject2Bytes(telegramIn, bool);
        byte[] arrayOfByte2 = WtcConnector.connectTxd(arrayOfByte1);
        if (arrayOfByte2 == null)
            return null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte2);
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOut telegramOut1 = telegramBuilder.getTelegramOutData(paramObject2);
            ByteDecoder byteDecoder1 = new ByteDecoder();
            TelegramOut telegramOut2 = (TelegramOut) byteDecoder1.convertBytes2Object(arrayOfByte2, telegramOut1, bool);
            TelegramTail telegramTail1 = telegramOut2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                object = telegramOut2.getData().getData();
            } else {
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        "Response Telegram Length is not Matched !!");
                throw telegramNestedRuntimeException;
            }
            TelegramMessage telegramMessage1 = telegramOut2.getMessage();
            TelegramOutputUserData telegramOutputUserData1 = new TelegramOutputUserData();
            telegramOutputUserData1.setMessage(telegramMessage1);
            telegramOutputUserData1.setOutput(object);
            telegramOutputUserData1.setHeader(telegramHeader);
            return telegramOutputUserData1;
        }
        TelegramOutNoData telegramOutNoData1 = telegramBuilder.getTelegramOutDataNoData();
        ByteDecoder byteDecoder = new ByteDecoder();
        TelegramOutNoData telegramOutNoData2 = (TelegramOutNoData) byteDecoder.convertBytes2Object(arrayOfByte2,
                telegramOutNoData1, bool);
        TelegramTail telegramTail = telegramOutNoData2.getTail();
        if (!"@@".equals(telegramTail.getTail())) {
            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                    "Response Telegram Length is not Matched !!");
            throw telegramNestedRuntimeException;
        }
        TelegramMessage telegramMessage = telegramOutNoData2.getMessage();
        TelegramOutputUserData telegramOutputUserData = new TelegramOutputUserData();
        telegramOutputUserData.setMessage(telegramMessage);
        telegramOutputUserData.setOutput(object);
        telegramOutputUserData.setHeader(telegramHeader);
        return telegramOutputUserData;
    }

    public static TelegramOutputUserData interfaceTuxedo(TelegramInputUserData paramTelegramInputUserData,
            List<Object> paramList, Object paramObject) throws Exception {
        boolean bool = true;
        Object object = null;
        TelegramBuilder telegramBuilder = new TelegramBuilder();
        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramInList telegramInList = telegramBuilder.getTelegramInList(paramTelegramInputUserData, paramList);
        byte[] arrayOfByte1 = byteEncoder.convertObject2Bytes(telegramInList, bool);
        byte[] arrayOfByte2 = WtcConnector.connectTxd(arrayOfByte1);
        if (arrayOfByte2 == null)
            return null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte2);
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOut telegramOut1 = telegramBuilder.getTelegramOutData(paramObject);
            ByteDecoder byteDecoder1 = new ByteDecoder();
            TelegramOut telegramOut2 = (TelegramOut) byteDecoder1.convertBytes2Object(arrayOfByte2, telegramOut1, bool);
            TelegramTail telegramTail1 = telegramOut2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                object = telegramOut2.getData().getData();
            } else {
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        "Response Telegram Length is not Matched !!");
                throw telegramNestedRuntimeException;
            }
            TelegramMessage telegramMessage1 = telegramOut2.getMessage();
            TelegramOutputUserData telegramOutputUserData1 = new TelegramOutputUserData();
            telegramOutputUserData1.setMessage(telegramMessage1);
            telegramOutputUserData1.setOutput(object);
            telegramOutputUserData1.setHeader(telegramHeader);
            return telegramOutputUserData1;
        }
        TelegramOutNoData telegramOutNoData1 = telegramBuilder.getTelegramOutDataNoData();
        ByteDecoder byteDecoder = new ByteDecoder();
        TelegramOutNoData telegramOutNoData2 = (TelegramOutNoData) byteDecoder.convertBytes2Object(arrayOfByte2,
                telegramOutNoData1, bool);
        TelegramTail telegramTail = telegramOutNoData2.getTail();
        if (!"@@".equals(telegramTail.getTail())) {
            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                    "Response Telegram Length is not Matched !!");
            throw telegramNestedRuntimeException;
        }
        TelegramMessage telegramMessage = telegramOutNoData2.getMessage();
        TelegramOutputUserData telegramOutputUserData = new TelegramOutputUserData();
        telegramOutputUserData.setMessage(telegramMessage);
        telegramOutputUserData.setOutput(object);
        telegramOutputUserData.setHeader(telegramHeader);
        return telegramOutputUserData;
    }

    public static TelegramOutputUserData interfaceTuxedo(TelegramInputUserData paramTelegramInputUserData,
            Object paramObject, List<Object> paramList) throws Exception {
        boolean bool = true;
        List list = null;
        TelegramBuilder telegramBuilder = new TelegramBuilder();
        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn telegramIn = telegramBuilder.getTelegramIn(paramTelegramInputUserData, paramObject);
        byte[] arrayOfByte1 = byteEncoder.convertObject2Bytes(telegramIn, bool);
        byte[] arrayOfByte2 = WtcConnector.connectTxd(arrayOfByte1);
        if (arrayOfByte2 == null)
            return null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte2);
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOutList telegramOutList1 = telegramBuilder.getTelegramOutDataList(paramList);
            ByteDecoder byteDecoder1 = new ByteDecoder();
            TelegramOutList telegramOutList2 = (TelegramOutList) byteDecoder1.convertBytes2Object(arrayOfByte2,
                    telegramOutList1, bool);
            TelegramTail telegramTail1 = telegramOutList2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                list = telegramOutList2.getData().getData();
            } else {
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        "Response Telegram Length is not Matched !!");
                throw telegramNestedRuntimeException;
            }
            TelegramMessage telegramMessage1 = telegramOutList2.getMessage();
            TelegramOutputUserData telegramOutputUserData1 = new TelegramOutputUserData();
            telegramOutputUserData1.setMessage(telegramMessage1);
            telegramOutputUserData1.setOutputList(list);
            telegramOutputUserData1.setHeader(telegramHeader);
            return telegramOutputUserData1;
        }
        TelegramOutNoData telegramOutNoData1 = telegramBuilder.getTelegramOutDataNoData();
        ByteDecoder byteDecoder = new ByteDecoder();
        TelegramOutNoData telegramOutNoData2 = (TelegramOutNoData) byteDecoder.convertBytes2Object(arrayOfByte2,
                telegramOutNoData1, bool);
        TelegramTail telegramTail = telegramOutNoData2.getTail();
        if (!"@@".equals(telegramTail.getTail())) {
            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                    "Response Telegram Length is not Matched !!");
            throw telegramNestedRuntimeException;
        }
        TelegramMessage telegramMessage = telegramOutNoData2.getMessage();
        TelegramOutputUserData telegramOutputUserData = new TelegramOutputUserData();
        telegramOutputUserData.setMessage(telegramMessage);
        telegramOutputUserData.setOutput(list);
        telegramOutputUserData.setHeader(telegramHeader);
        return telegramOutputUserData;
    }

    public static TelegramOutputUserData interfaceTuxedo(TelegramInputUserData paramTelegramInputUserData,
            List<Object> paramList1, List<Object> paramList2) throws Exception {
        boolean bool = true;
        List list = null;
        TelegramBuilder telegramBuilder = new TelegramBuilder();
        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramInList telegramInList = telegramBuilder.getTelegramInList(paramTelegramInputUserData, paramList1);
        byte[] arrayOfByte1 = byteEncoder.convertObject2Bytes(telegramInList, bool);
        byte[] arrayOfByte2 = WtcConnector.connectTxd(arrayOfByte1);
        if (arrayOfByte2 == null)
            return null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte2);
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOutList telegramOutList1 = telegramBuilder.getTelegramOutDataList(paramList2);
            ByteDecoder byteDecoder1 = new ByteDecoder();
            TelegramOutList telegramOutList2 = (TelegramOutList) byteDecoder1.convertBytes2Object(arrayOfByte2,
                    telegramOutList1, bool);
            TelegramTail telegramTail1 = telegramOutList2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                list = telegramOutList2.getData().getData();
            } else {
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        "Response Telegram Length is not Matched !!");
                throw telegramNestedRuntimeException;
            }
            TelegramMessage telegramMessage1 = telegramOutList2.getMessage();
            TelegramOutputUserData telegramOutputUserData1 = new TelegramOutputUserData();
            telegramOutputUserData1.setMessage(telegramMessage1);
            telegramOutputUserData1.setOutputList(list);
            telegramOutputUserData1.setHeader(telegramHeader);
            return telegramOutputUserData1;
        }
        TelegramOutNoData telegramOutNoData1 = telegramBuilder.getTelegramOutDataNoData();
        ByteDecoder byteDecoder = new ByteDecoder();
        TelegramOutNoData telegramOutNoData2 = (TelegramOutNoData) byteDecoder.convertBytes2Object(arrayOfByte2,
                telegramOutNoData1, bool);
        TelegramTail telegramTail = telegramOutNoData2.getTail();
        if (!"@@".equals(telegramTail.getTail())) {
            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                    "Response Telegram Length is not Matched !!");
            throw telegramNestedRuntimeException;
        }
        TelegramMessage telegramMessage = telegramOutNoData2.getMessage();
        TelegramOutputUserData telegramOutputUserData = new TelegramOutputUserData();
        telegramOutputUserData.setMessage(telegramMessage);
        telegramOutputUserData.setOutput(list);
        telegramOutputUserData.setHeader(telegramHeader);
        return telegramOutputUserData;
    }

    public static TelegramOutputUserData interfaceTuxedo(TelegramInputUserData paramTelegramInputUserData,
            Object paramObject1, Object paramObject2, boolean paramBoolean) throws Exception {
        Object object = null;
        TelegramBuilder telegramBuilder = new TelegramBuilder();
        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn telegramIn = telegramBuilder.getTelegramIn(paramTelegramInputUserData, paramObject1);
        byte[] arrayOfByte1 = byteEncoder.convertObject2Bytes(telegramIn, paramBoolean);
        byte[] arrayOfByte2 = WtcConnector.connectTxd(arrayOfByte1);
        if (arrayOfByte2 == null)
            return null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte2);
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOut telegramOut1 = telegramBuilder.getTelegramOutData(paramObject2);
            ByteDecoder byteDecoder1 = new ByteDecoder();
            TelegramOut telegramOut2 = (TelegramOut) byteDecoder1.convertBytes2Object(arrayOfByte2, telegramOut1,
                    paramBoolean);
            TelegramTail telegramTail1 = telegramOut2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                object = telegramOut2.getData().getData();
            } else {
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        "Response Telegram Length is not Matched !!");
                throw telegramNestedRuntimeException;
            }
            TelegramMessage telegramMessage1 = telegramOut2.getMessage();
            TelegramOutputUserData telegramOutputUserData1 = new TelegramOutputUserData();
            telegramOutputUserData1.setMessage(telegramMessage1);
            telegramOutputUserData1.setOutput(object);
            telegramOutputUserData1.setHeader(telegramHeader);
            return telegramOutputUserData1;
        }
        TelegramOutNoData telegramOutNoData1 = telegramBuilder.getTelegramOutDataNoData();
        ByteDecoder byteDecoder = new ByteDecoder();
        TelegramOutNoData telegramOutNoData2 = (TelegramOutNoData) byteDecoder.convertBytes2Object(arrayOfByte2,
                telegramOutNoData1, paramBoolean);
        TelegramTail telegramTail = telegramOutNoData2.getTail();
        if (!"@@".equals(telegramTail.getTail())) {
            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                    "Response Telegram Length is not Matched !!");
            throw telegramNestedRuntimeException;
        }
        TelegramMessage telegramMessage = telegramOutNoData2.getMessage();
        TelegramOutputUserData telegramOutputUserData = new TelegramOutputUserData();
        telegramOutputUserData.setMessage(telegramMessage);
        telegramOutputUserData.setOutput(object);
        telegramOutputUserData.setHeader(telegramHeader);
        return telegramOutputUserData;
    }

    public static TelegramOutputUserData interfaceTuxedo(TelegramInputUserData paramTelegramInputUserData,
            List<Object> paramList, Object paramObject, boolean paramBoolean) throws Exception {
        Object object = null;
        TelegramBuilder telegramBuilder = new TelegramBuilder();
        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramInList telegramInList = telegramBuilder.getTelegramInList(paramTelegramInputUserData, paramList);
        byte[] arrayOfByte1 = byteEncoder.convertObject2Bytes(telegramInList, paramBoolean);
        byte[] arrayOfByte2 = WtcConnector.connectTxd(arrayOfByte1);
        if (arrayOfByte2 == null)
            return null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte2);
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOut telegramOut1 = telegramBuilder.getTelegramOutData(paramObject);
            ByteDecoder byteDecoder1 = new ByteDecoder();
            TelegramOut telegramOut2 = (TelegramOut) byteDecoder1.convertBytes2Object(arrayOfByte2, telegramOut1,
                    paramBoolean);
            TelegramTail telegramTail1 = telegramOut2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                object = telegramOut2.getData().getData();
            } else {
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        "Response Telegram Length is not Matched !!");
                throw telegramNestedRuntimeException;
            }
            TelegramMessage telegramMessage1 = telegramOut2.getMessage();
            TelegramOutputUserData telegramOutputUserData1 = new TelegramOutputUserData();
            telegramOutputUserData1.setMessage(telegramMessage1);
            telegramOutputUserData1.setOutput(object);
            telegramOutputUserData1.setHeader(telegramHeader);
            return telegramOutputUserData1;
        }
        TelegramOutNoData telegramOutNoData1 = telegramBuilder.getTelegramOutDataNoData();
        ByteDecoder byteDecoder = new ByteDecoder();
        TelegramOutNoData telegramOutNoData2 = (TelegramOutNoData) byteDecoder.convertBytes2Object(arrayOfByte2,
                telegramOutNoData1, paramBoolean);
        TelegramTail telegramTail = telegramOutNoData2.getTail();
        if (!"@@".equals(telegramTail.getTail())) {
            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                    "Response Telegram Length is not Matched !!");
            throw telegramNestedRuntimeException;
        }
        TelegramMessage telegramMessage = telegramOutNoData2.getMessage();
        TelegramOutputUserData telegramOutputUserData = new TelegramOutputUserData();
        telegramOutputUserData.setMessage(telegramMessage);
        telegramOutputUserData.setOutput(object);
        telegramOutputUserData.setHeader(telegramHeader);
        return telegramOutputUserData;
    }

    public static TelegramOutputUserData interfaceTuxedo(TelegramInputUserData paramTelegramInputUserData,
            Object paramObject, List<Object> paramList, boolean paramBoolean) throws Exception {
        List list = null;
        TelegramBuilder telegramBuilder = new TelegramBuilder();
        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramIn telegramIn = telegramBuilder.getTelegramIn(paramTelegramInputUserData, paramObject);
        byte[] arrayOfByte1 = byteEncoder.convertObject2Bytes(telegramIn, paramBoolean);
        byte[] arrayOfByte2 = WtcConnector.connectTxd(arrayOfByte1);
        if (arrayOfByte2 == null)
            return null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte2);
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOutList telegramOutList1 = telegramBuilder.getTelegramOutDataList(paramList);
            ByteDecoder byteDecoder1 = new ByteDecoder();
            TelegramOutList telegramOutList2 = (TelegramOutList) byteDecoder1.convertBytes2Object(arrayOfByte2,
                    telegramOutList1, paramBoolean);
            TelegramTail telegramTail1 = telegramOutList2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                list = telegramOutList2.getData().getData();
            } else {
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        "Response Telegram Length is not Matched !!");
                throw telegramNestedRuntimeException;
            }
            TelegramMessage telegramMessage1 = telegramOutList2.getMessage();
            TelegramOutputUserData telegramOutputUserData1 = new TelegramOutputUserData();
            telegramOutputUserData1.setMessage(telegramMessage1);
            telegramOutputUserData1.setOutputList(list);
            telegramOutputUserData1.setHeader(telegramHeader);
            return telegramOutputUserData1;
        }
        TelegramOutNoData telegramOutNoData1 = telegramBuilder.getTelegramOutDataNoData();
        ByteDecoder byteDecoder = new ByteDecoder();
        TelegramOutNoData telegramOutNoData2 = (TelegramOutNoData) byteDecoder.convertBytes2Object(arrayOfByte2,
                telegramOutNoData1, paramBoolean);
        TelegramTail telegramTail = telegramOutNoData2.getTail();
        if (!"@@".equals(telegramTail.getTail())) {
            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                    "Response Telegram Length is not Matched !!");
            throw telegramNestedRuntimeException;
        }
        TelegramMessage telegramMessage = telegramOutNoData2.getMessage();
        TelegramOutputUserData telegramOutputUserData = new TelegramOutputUserData();
        telegramOutputUserData.setMessage(telegramMessage);
        telegramOutputUserData.setOutput(list);
        telegramOutputUserData.setHeader(telegramHeader);
        return telegramOutputUserData;
    }

    public static TelegramOutputUserData interfaceTuxedo(TelegramInputUserData paramTelegramInputUserData,
            List<Object> paramList1, List<Object> paramList2, boolean paramBoolean) throws Exception {
        List list = null;
        TelegramBuilder telegramBuilder = new TelegramBuilder();
        ByteEncoder byteEncoder = new ByteEncoder();
        TelegramInList telegramInList = telegramBuilder.getTelegramInList(paramTelegramInputUserData, paramList1);
        byte[] arrayOfByte1 = byteEncoder.convertObject2Bytes(telegramInList, paramBoolean);
        byte[] arrayOfByte2 = WtcConnector.connectTxd(arrayOfByte1);
        if (arrayOfByte2 == null)
            return null;
        TelegramHeader telegramHeader = getHeaderFromBytes(arrayOfByte2);
        if (telegramHeader.getErr_flag() == 0) {
            TelegramOutList telegramOutList1 = telegramBuilder.getTelegramOutDataList(paramList2);
            ByteDecoder byteDecoder1 = new ByteDecoder();
            TelegramOutList telegramOutList2 = (TelegramOutList) byteDecoder1.convertBytes2Object(arrayOfByte2,
                    telegramOutList1, paramBoolean);
            TelegramTail telegramTail1 = telegramOutList2.getTail();
            if ("@@".equals(telegramTail1.getTail())) {
                list = telegramOutList2.getData().getData();
            } else {
                TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                        "Response Telegram Length is not Matched !!");
                throw telegramNestedRuntimeException;
            }
            TelegramMessage telegramMessage1 = telegramOutList2.getMessage();
            TelegramOutputUserData telegramOutputUserData1 = new TelegramOutputUserData();
            telegramOutputUserData1.setMessage(telegramMessage1);
            telegramOutputUserData1.setOutputList(list);
            telegramOutputUserData1.setHeader(telegramHeader);
            return telegramOutputUserData1;
        }
        TelegramOutNoData telegramOutNoData1 = telegramBuilder.getTelegramOutDataNoData();
        ByteDecoder byteDecoder = new ByteDecoder();
        TelegramOutNoData telegramOutNoData2 = (TelegramOutNoData) byteDecoder.convertBytes2Object(arrayOfByte2,
                telegramOutNoData1, paramBoolean);
        TelegramTail telegramTail = telegramOutNoData2.getTail();
        if (!"@@".equals(telegramTail.getTail())) {
            TelegramNestedRuntimeException telegramNestedRuntimeException = new TelegramNestedRuntimeException(
                    "Response Telegram Length is not Matched !!");
            throw telegramNestedRuntimeException;
        }
        TelegramMessage telegramMessage = telegramOutNoData2.getMessage();
        TelegramOutputUserData telegramOutputUserData = new TelegramOutputUserData();
        telegramOutputUserData.setMessage(telegramMessage);
        telegramOutputUserData.setOutput(list);
        telegramOutputUserData.setHeader(telegramHeader);
        return telegramOutputUserData;
    }

    public static TelegramHeader getHeaderFromBytes(byte[] paramArrayOfbyte) throws Exception {
        TelegramHeader telegramHeader = new TelegramHeader();
        try {
            byte[] arrayOfByte = TelegramUtil.cutBytes(paramArrayOfbyte, 0, 500);
            ByteDecoder byteDecoder = new ByteDecoder();
            telegramHeader = (TelegramHeader) byteDecoder.convertBytes2Object(arrayOfByte, telegramHeader, true);
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
            throw exception;
        }
        return telegramHeader;
    }

    public static TelegramMessage getMessageFromBytes(byte[] paramArrayOfbyte) throws Exception {
        TelegramMessage telegramMessage = new TelegramMessage();
        try {
            int i = paramArrayOfbyte.length - 500;
            byte[] arrayOfByte = TelegramUtil.cutBytes(paramArrayOfbyte, 500, i);
            ByteDecoder byteDecoder = new ByteDecoder();
            telegramMessage = (TelegramMessage) byteDecoder.convertBytes2Object(arrayOfByte, telegramMessage, true);
        } catch (Exception exception) {
            ExceptionUtil.logPrintStackTrace(logger, exception);
            throw exception;
        }
        return telegramMessage;
    }
}
