package com.hex.bigdata.udsp.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 2017/6/7.
 */
public class ComExcelEnums {
    final static String sortNum = "sortNum";

    public enum DataSourceProperties implements ExcelProperty {
        Excel_0(sortNum, "0"),
        Excel_1("name", "1"),
        Excel_2("value", "2"),
        Excel_3("describe", "3");

        private String value;
        private String name;

        private static List<DataSourceProperties> enums;

        private DataSourceProperties(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public enum IqApplicationQueryCoumn implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("name", "1"),
        Excel_2("describe", "2"),
        Excel_3("operator", "3"),
        Excel_4("type", "4"),
        Excel_5("length", "5"),
        Excel_6("defaultVal", "6"),
        Excel_7("label", "7"),
        Excel_8("isNeed", "8"),
        Excel_9("isOfferOut", "9");


        private String value;
        private String name;

        private static List<IqApplicationQueryCoumn> enums;

        private IqApplicationQueryCoumn(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);

                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
                enums.add(Excel_7);
                enums.add(Excel_8);
                enums.add(Excel_9);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);

                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
                enums.add(Excel_7);
                enums.add(Excel_8);
                enums.add(Excel_9);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public enum IqApplicationReturnCol implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("name", "1"),
        Excel_2("describe", "2"),
        Excel_3("type", "3"),
        Excel_4("length", "4"),
        Excel_5("stats", "5"),
        Excel_6("label", "6");


        private String value;
        private String name;

        private static List<IqApplicationReturnCol> enums;

        private IqApplicationReturnCol(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum IqApplicationOrderCol implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("name", "1"),
        Excel_2("describe", "2"),
        Excel_3("type", "3"),
        Excel_4("orderType", "4");


        private String value;
        private String name;

        private static List<IqApplicationOrderCol> enums;

        private IqApplicationOrderCol(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public enum IqMetadataCol implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("name", "1"),
        Excel_2("describe", "2"),
        Excel_3("colType", "3"),
        Excel_4("length", "4"),
        Excel_5("note", "5");


        private String value;
        private String name;

        private static List<IqMetadataCol> enums;

        private IqMetadataCol(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public enum MmContractor implements ExcelProperty {
        Excel_0(sortNum, "0"),
        Excel_1("cnName", "1"),
        Excel_2("name", "2"),
        Excel_3("httpUrl", "3"),
        Excel_4("principal", "4"),
        Excel_5("mobile", "5"),
        Excel_6("ftpPassword", "6"),
        Excel_7("note", "7");

        private String value;
        private String name;

        private static List<MmContractor> enums;

        private MmContractor(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
                enums.add(Excel_7);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
                enums.add(Excel_7);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum ModelParam implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("name", "1"),
        Excel_2("describe", "2"),
        Excel_3("colType", "3"),
        Excel_4("length", "4"),
        Excel_5("note", "5");


        private String value;
        private String name;

        private static List<ModelParam> enums;

        private ModelParam(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public enum MmAppliactionExecuteParam implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("name", "1"),
        Excel_2("describe", "2"),
        Excel_3("defaultVal", "3"),
        Excel_4("isNeed", "4");


        private String value;
        private String name;

        private static List<MmAppliactionExecuteParam> enums;

        private MmAppliactionExecuteParam(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public enum MmAppliactionReturnParam implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("name", "1"),
        Excel_2("describe", "2");


        private String value;
        private String name;

        private static List<MmAppliactionReturnParam> enums;

        private MmAppliactionReturnParam(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public enum RtsMatedata implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("name", "1"),
        Excel_2("describe", "2");


        private String value;
        private String name;

        private static List<RtsMatedata> enums;

        private RtsMatedata(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public enum Comproperties implements ExcelProperty {
        Excel_0(sortNum, "0"),
        Excel_1("name", "1"),
        Excel_2("value", "2"),
        Excel_3("describe", "3");


        private String value;
        private String name;

        private static List<Comproperties> enums;

        private Comproperties(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public enum RcService implements ExcelProperty {
        Excel_0(sortNum, "0"),
        Excel_1("name", "1"),
        Excel_2("type", "2"),
        Excel_3("appId", "3"),
        Excel_4("describe", "4"),
        Excel_5("status", "5");

        private String value;
        private String name;

        private static List<RcService> enums;

        private RcService(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public enum RcUserService implements ExcelProperty {
        Excel_0(sortNum, "0"),
        Excel_1("serviceId", "1"),
        Excel_2("userId", "2"),
        Excel_3("ipSection", "3"),
        Excel_4("maxSyncNum", "4"),
        Excel_5("maxAsyncNum", "5"),
        Excel_6("maxSyncWaitNum", "6"),
        Excel_7("maxAsyncWaitNum", "7"),
        Excel_8("maxSyncWaitTimeout", "8"),
        Excel_9("maxAsyncWaitTimeout", "9"),
        Excel_10("maxSyncExecuteTimeout", "10"),
        Excel_11("maxAsyncExecuteTimeout", "11");

        private String value;
        private String name;

        private static List<RcUserService> enums;

        private RcUserService(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
                enums.add(Excel_7);
                enums.add(Excel_8);
                enums.add(Excel_9);
                enums.add(Excel_10);
                enums.add(Excel_11);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum OLQApplicationParamCoumn implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("paramName", "1"),
        Excel_2("paramDesc", "2"),
        Excel_3("defaultValue", "3"),
        Excel_4("isNeed", "4");

        private String value;
        private String name;

        private static List<OLQApplicationParamCoumn> enums;

        private OLQApplicationParamCoumn(String na, String va) {
            value = va;
            name = na;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
            }
            return enums;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum ImMapping implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("name", "1"),
        Excel_2("colId", "2"),
        Excel_3("type", "3"),
        Excel_4("length", "4"),
        Excel_5("describe", "5"),
        Excel_6("note", "6");

        private String value;
        private String name;

        private static List<ImMapping> enums;

        private ImMapping(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



    public enum ImModelFiterCol implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("name", "1"),
        Excel_2("describe", "2"),
        Excel_3("type", "3"),
        Excel_4("length", "4"),
        Excel_5("isNeed", "5"),
        Excel_6("operator", "6"),
        Excel_7("defaultVal", "7"),
        Excel_8("label", "8");

        private String value;
        private String name;

        private static List<ImModelFiterCol> enums;

        private ImModelFiterCol(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
                enums.add(Excel_7);
                enums.add(Excel_8);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
                enums.add(Excel_7);
                enums.add(Excel_8);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


    public enum ImMetadataCol implements ExcelProperty {
        Excel_0("seq", "0"),
        Excel_1("name", "1"),
        Excel_2("describe", "2"),
        Excel_3("type", "3"),
        Excel_4("length", "4"),
        Excel_5("note", "5"),
        Excel_6("indexed", "6"),
        Excel_7("primary", "7"),
        Excel_8("stored", "8");

        private String value;
        private String name;

        private static List<ImMetadataCol> enums;

        private ImMetadataCol(String na, String va) {
            value = va;
            name = na;
        }

        public static List getAllNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
                enums.add(Excel_7);
                enums.add(Excel_8);
            }
            return enums;
        }

        public List getNums() {
            if (enums == null) {
                enums = new ArrayList();
                enums.add(Excel_0);
                enums.add(Excel_1);
                enums.add(Excel_2);
                enums.add(Excel_3);
                enums.add(Excel_4);
                enums.add(Excel_5);
                enums.add(Excel_6);
                enums.add(Excel_7);
                enums.add(Excel_8);
            }
            return enums;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
