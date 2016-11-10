package com.york.user.numerology.YorkLifeCode;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Lunar {   
   /*
    public static void main(String[] args) {   
        Lunar l = new Lunar(System.currentTimeMillis());   
        System.out.println("節氣:" + l.getTermString());   
        System.out.println("干支曆:" + l.getCyclicalDateString());   
        System.out.println("星期" + l.getDayOfWeek());   
        System.out.println("農曆" + l.getLunarDateString());   
    }   */

    private final static int[] lunarInfo = {
        0x4bd8, 0x4ae0, 0xa570, 0x54d5, 0xd260, 0xd950, 0x5554, 0x56af,
        0x9ad0, 0x55d2, 0x4ae0, 0xa5b6, 0xa4d0, 0xd250, 0xd295, 0xb54f,
        0xd6a0, 0xada2, 0x95b0, 0x4977, 0x497f, 0xa4b0, 0xb4b5, 0x6a50,
        0x6d40, 0xab54, 0x2b6f, 0x9570, 0x52f2, 0x4970, 0x6566, 0xd4a0,
        0xea50, 0x6a95, 0x5adf, 0x2b60, 0x86e3, 0x92ef, 0xc8d7, 0xc95f,
        0xd4a0, 0xd8a6, 0xb55f, 0x56a0, 0xa5b4, 0x25df, 0x92d0, 0xd2b2,
        0xa950, 0xb557, 0x6ca0, 0xb550, 0x5355, 0x4daf, 0xa5b0, 0x4573,
        0x52bf, 0xa9a8, 0xe950, 0x6aa0, 0xaea6, 0xab50, 0x4b60, 0xaae4,
        0xa570, 0x5260, 0xf263, 0xd950, 0x5b57, 0x56a0, 0x96d0, 0x4dd5,
        0x4ad0, 0xa4d0, 0xd4d4, 0xd250, 0xd558, 0xb540, 0xb6a0, 0x95a6,
        0x95bf, 0x49b0, 0xa974, 0xa4b0, 0xb27a, 0x6a50, 0x6d40, 0xaf46,
        0xab60, 0x9570, 0x4af5, 0x4970, 0x64b0, 0x74a3, 0xea50, 0x6b58,
        0x5ac0, 0xab60, 0x96d5, 0x92e0, 0xc960, 0xd954, 0xd4a0, 0xda50,
        0x7552, 0x56a0, 0xabb7, 0x25d0, 0x92d0, 0xcab5, 0xa950, 0xb4a0,
        0xbaa4, 0xad50, 0x55d9, 0x4ba0, 0xa5b0, 0x5176, 0x52bf, 0xa930,
        0x7954, 0x6aa0, 0xad50, 0x5b52, 0x4b60, 0xa6e6, 0xa4e0, 0xd260,
        0xea65, 0xd530, 0x5aa0, 0x76a3, 0x96d0, 0x4afb, 0x4ad0, 0xa4d0,
        0xd0b6, 0xd25f, 0xd520, 0xdd45, 0xb5a0, 0x56d0, 0x55b2, 0x49b0,
        0xa577, 0xa4b0, 0xaa50, 0xb255, 0x6d2f, 0xada0, 0x4b63, 0x937f,
        0x49f8, 0x4970, 0x64b0, 0x68a6, 0xea5f, 0x6b20, 0xa6c4, 0xaaef,
        0x92e0, 0xd2e3, 0xc960, 0xd557, 0xd4a0, 0xda50, 0x5d55, 0x56a0,
        0xa6d0, 0x55d4, 0x52d0, 0xa9b8, 0xa950, 0xb4a0, 0xb6a6, 0xad50,
        0x55a0, 0xaba4, 0xa5b0, 0x52b0, 0xb273, 0x6930, 0x7337, 0x6aa0,
        0xad50, 0x4b55, 0x4b6f, 0xa570, 0x54e4, 0xd260, 0xe968, 0xd520,
        0xdaa0, 0x6aa6, 0x56df, 0x4ae0, 0xa9d4, 0xa4d0, 0xd150, 0xf252, 0xd520
    };
    private final static int[] solarTermInfo = {
             0,  21208,  42467,  63836,  85337, 107014, 128867, 150921,
        173149, 195551, 218072, 240693, 263343, 285989, 308563, 331033,
        353350, 375494, 397447, 419210, 440795, 462224, 483532, 504758
    };
    public final static String[] Tianan = {
        "甲", "乙", "丙", "丁", "戊", "己", "庚", "辛", "壬", "癸"
    };
    public final static String[] Deqi = {
        "子", "丑", "寅", "卯", "辰", "巳", "午", "未", "申", "酉", "戌", "亥"
    };
    public final static String[] Animals = {
        "鼠", "牛", "虎", "兔", "龍", "蛇", "馬", "羊", "猴", "雞", "狗", "豬"
    };
    public final static String[] solarTerm = {
        "小寒", "大寒", "立春", "雨水", "驚蟄", "春分",
        "清明", "穀雨", "立夏", "小滿", "芒種", "夏至",
        "小暑", "大暑", "立秋", "處暑", "白露", "秋分",
        "寒露", "霜降", "立冬", "小雪", "大雪", "冬至"
    };
    public final static String[] lunarString1 = {
        "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"
    };
    public final static String[] lunarString2 = {
        "初", "十", "廿", "卅", "正", "臘", "冬", "閏"
    };

    /**
     * 國曆節日 *表示放假日
     */
    private final static String[] sFtv = {
        "0101*元旦",      "0214 情人節", "0308 婦女節",     "0312 植樹節",
        "0315 消費者權益日",  "0401 愚人節", "0501*勞動節",     "0504 青年節",
        "0509 郝維節",     "0512 護士節", "0601 兒童節",     "0701 建黨節 香港回歸紀念",
        "0801 建軍節",     "0808 父親節", "0816 燕銜泥節",    "0909 毛澤東逝世紀念",
        "0910 教師節",     "0928 孔子誕辰","1001*國慶日",     "1006 老人節",
        "1024 聯合國日",    "1111 光棍節", "1112 孫中山誕辰紀念", "1220 澳門回歸紀念",
        "1225 耶誕節",     "1226 毛澤東誕辰紀念"
    };
    /**
     * 農曆節日 *表示放假日
     */
    private final static String[] lFtv = {
        "0101*春節、彌勒佛誕", "0106 定光佛誕",    "0115 元宵節",
        "0208 釋迦牟尼佛出家", "0215 釋迦牟尼佛涅槃", "0209 海空上師誕",
        "0219 觀世音菩薩誕",  "0221 普賢菩薩誕",   "0316 准提菩薩誕",
        "0404 文殊菩薩誕",   "0408 釋迦牟尼佛誕",  "0415 佛吉祥日——釋迦牟尼佛誕生、成道、涅槃三期同一慶(即南傳佛教國家的衛塞節)",
        "0505 端午節",     "0513 伽藍菩薩誕",   "0603 護法韋馱尊天菩薩誕",
        "0619 觀世音菩薩成道——此日放生、念佛，功德殊勝",
        "0707 七夕情人節",   "0713 大勢至菩薩誕",  "0715 中元節",
        "0724 龍樹菩薩誕",   "0730 地藏菩薩誕",   "0815 中秋節",
        "0822 燃燈佛誕",    "0909 重陽節",     "0919 觀世音菩薩出家紀念日",
        "0930 藥師琉璃光如來誕","1005 達摩祖師誕",   "1107 阿彌陀佛誕",
        "1208 釋迦如來成道日，臘八節",         "1224 小年",
        "1229 華嚴菩薩誕",   "0100*除夕"
    };
    /**
     * 某月的第幾個星期幾
     */
    private static String[] wFtv = {
        "0520 母親節", "0716 合作節", "0730 被奴役國家周"
    };


    private static int toInt(String str) {
        try { return Integer.parseInt(str); }
        catch(Exception e) { return -1; }
    }
    private final static Pattern sFreg = Pattern.compile("^(\\d{2})(\\d{2})([\\s\\*])(.+)$");
    private final static Pattern wFreg = Pattern.compile("^(\\d{2})(\\d)(\\d)([\\s\\*])(.+)$");
    private synchronized void findFestival() {
        int sM = this.getSolarMonth();
        int sD = this.getSolarDay();
        int lM = this.getLunarMonth();
        int lD = this.getLunarDay();
        int sy = this.getSolarYear();
        Matcher m;
        for (int i=0; i<Lunar.sFtv.length; i++) {
            m = Lunar.sFreg.matcher(Lunar.sFtv[i]);
            if (m.find()) {
                if (sM == Lunar.toInt(m.group(1)) && sD == Lunar.toInt(m.group(2))) {
                    this.isSFestival = true;
                    this.sFestivalName = m.group(4);
                    if ("*".equals(m.group(3))) this.isHoliday = true;
                    break;
                }
            }
        }
        for (int i=0; i<Lunar.lFtv.length; i++) {
            m = Lunar.sFreg.matcher(Lunar.lFtv[i]);
            if (m.find()) {
                if (lM == Lunar.toInt(m.group(1)) && lD == Lunar.toInt(m.group(2))) {
                    this.isLFestival = true;
                    this.lFestivalName = m.group(4);
                    if ("*".equals(m.group(3))) this.isHoliday = true;
                    break;
                }
            }
        }

        // 月周節日    
        int w, d;
        for (int i=0; i<Lunar.wFtv.length; i++) {
            m = Lunar.wFreg.matcher(Lunar.wFtv[i]);
            if (m.find()) {
                if (this.getSolarMonth() == Lunar.toInt(m.group(1))) {
                    w = Lunar.toInt(m.group(2));
                    d = Lunar.toInt(m.group(3));
                    if (this.solar.get(Calendar.WEEK_OF_MONTH)==w &&
                            this.solar.get(Calendar.DAY_OF_WEEK)==d) {
                        this.isSFestival = true;
                        this.sFestivalName += "|" + m.group(5);
                        if ("*".equals(m.group(4))) this.isHoliday = true;
                    }
                }
            }
        }
        if(sy>1874 && sy<1909) this.description = "光緒" + (((sy-1874)==1)?"元":""+(sy-1874));
        if(sy>1908 && sy<1912) this.description = "宣統" + (((sy-1908)==1)?"元":String.valueOf(sy-1908));
        if(sy>1911 && sy<1950) this.description = "民國" + (((sy-1911)==1)?"元":String.valueOf(sy-1911));
        if(sy>1949) this.description = "共和國" + (((sy-1949)==1)?"元":String.valueOf(sy-1949));
        this.description += "年";
        this.sFestivalName = this.sFestivalName.replaceFirst("^\\|", "");
        this.isFinded = true;
    }

    private boolean isFinded = false;
    private boolean isSFestival = false;
    private boolean isLFestival = false;
    private String sFestivalName = "";
    private String lFestivalName = "";
    private String description = "";
    private boolean isHoliday = false;

    /**
     * 返回農曆年閏月月份
     * @param lunarYear
     *            指定農曆年份(數字)
     * @return 該農曆年閏月的月份(數字,沒閏返回0)
     */
    private static int getLunarLeapMonth(int lunarYear) {
        // 資料表中,每個農曆年用16bit來表示,    
        // 前12bit分別表示12個月份的大小月,最後4bit表示閏月    
        // 若4bit全為1或全為0,表示沒閏, 否則4bit的值為閏月月份    
        int leapMonth = Lunar.lunarInfo[lunarYear - 1900] & 0xf;
        leapMonth = (leapMonth == 0xf ? 0 : leapMonth);
        return leapMonth;
    }

    /**
     * 返回農曆年閏月的天數
     *
     * @param lunarYear 指定農曆年份(數字)
     * @return 該農曆年閏月的天數(數字)
     */
    private static int getLunarLeapDays(int lunarYear) {
        // 下一年最後4bit為1111,返回30(大月)    
        // 下一年最後4bit不為1111,返回29(小月)    
        // 若該年沒有閏月,返回0    
        return Lunar.getLunarLeapMonth(lunarYear) > 0 ? ((Lunar.lunarInfo[lunarYear - 1899] & 0xf) == 0xf ? 30
                : 29)
                : 0;
    }

    /**
     * 返回農曆年的總天數
     * @param lunarYear 指定農曆年份(數字)
     * @return 該農曆年的總天數(數字)
     */
    private static int getLunarYearDays(int lunarYear) {
        // 按小月計算,農曆年最少有12 * 29 = 348天    
        int daysInLunarYear = 348;
        // 資料表中,每個農曆年用16bit來表示,    
        // 前12bit分別表示12個月份的大小月,最後4bit表示閏月    
        // 每個大月累加一天    
        for (int i = 0x8000; i > 0x8; i >>= 1) {
            daysInLunarYear += ((Lunar.lunarInfo[lunarYear - 1900] & i) != 0) ? 1
                    : 0;
        }
        // 加上閏月天數    
        daysInLunarYear += Lunar.getLunarLeapDays(lunarYear);

        return daysInLunarYear;
    }

    /**
     * 返回農曆年正常月份的總天數
     * @param lunarYear
     *            指定農曆年份(數字)
     * @param lunarMonth
     *            指定農曆月份(數字)
     * @return 該農曆年閏月的月份(數字,沒閏返回0)
     */
    private static int getLunarMonthDays(int lunarYear, int lunarMonth) {
        // 資料表中,每個農曆年用16bit來表示,    
        // 前12bit分別表示12個月份的大小月,最後4bit表示閏月    
        int daysInLunarMonth = ((Lunar.lunarInfo[lunarYear - 1900] & (0x10000 >> lunarMonth)) != 0) ? 30
                : 29;
        return daysInLunarMonth;
    }
    /**
     * 取 Date 物件中用全球標準時間 (UTC) 表示的日期
     *
     * @param date 指定日期
     * @return UTC 全球標準時間 (UTC) 表示的日期
     */
    public static synchronized int getUTCDay(Date date) {
            Lunar.makeUTCCalendar();
            synchronized (utcCal) {
                utcCal.clear();
                utcCal.setTimeInMillis(date.getTime());
                return utcCal.get(Calendar.DAY_OF_MONTH);
            }
    }
    private static GregorianCalendar utcCal = null;
    private static synchronized void makeUTCCalendar() {
        if (Lunar.utcCal == null) {
            Lunar.utcCal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        }
    }
    /**
     * 返回全球標準時間 (UTC) (或 GMT) 的 1970 年 1 月 1 日到所指定日期之間所間隔的毫秒數。
     * @param y 指定年份
     * @param m 指定月份
     * @param d 指定日期
     * @param h 指定小時
     * @param min 指定分鐘
     * @param sec 指定秒數
     * @return 全球標準時間 (UTC) (或 GMT) 的 1970 年 1 月 1 日到所指定日期之間所間隔的毫秒數
     */
    public static synchronized long UTC(int y, int m, int d, int h, int min, int sec) {
        Lunar.makeUTCCalendar();
        synchronized (utcCal) {
            utcCal.clear();
            utcCal.set(y, m, d, h, min, sec);
            return utcCal.getTimeInMillis();
        }
    }

    /**
     * 返回西曆年節氣的日期
     * @param solarYear 指定西曆年份(數字)
     * @param index 指定節氣序號(數字,0從小寒算起)
     * @return 日期(數字,所在月份的第幾天)
     */
    private static int getSolarTermDay(int solarYear, int index) {
        long l = (long)31556925974.7 * (solarYear - 1900) +
        solarTermInfo[index] * 60000L;
        l = l + Lunar.UTC(1900,0,6,2,5,0);
        return Lunar.getUTCDay(new Date(l));
    }

    private Calendar solar;
    private int lunarYear;
    private int lunarMonth;
    private int lunarDay;
    private boolean isLeap;
    private boolean isLeapYear;
    private int solarYear;
    private int solarMonth;
    private int solarDay;
    private int hour;
    private int cyclicalYear = 0;
    private int cyclicalMonth = 0;
    private int cyclicalDay = 0;
    private int maxDayInMonth = 29;

    /**
     * 通過 Date 物件構建農曆資訊
     * @param date 指定日期對象
     */
    public Lunar(Date date) {
        if (date == null)
            date = new Date();
        this.init(date.getTime());
    }

    /**
     * 通過 TimeInMillis 構建農曆資訊
     * @param TimeInMillis
     */
    public Lunar(long TimeInMillis) {
        this.init(TimeInMillis);
    }


    private void init(long TimeInMillis) {
        this.solar = Calendar.getInstance();
        this.solar.setTimeInMillis(TimeInMillis);
        Calendar baseDate = new GregorianCalendar(1900, 0, 31);
        long offset = (TimeInMillis - baseDate.getTimeInMillis()) / 86400000;
        // 按農曆年遞減每年的農曆天數，確定農曆年份    
        this.lunarYear = 1900;
        int daysInLunarYear = Lunar.getLunarYearDays(this.lunarYear);
        while (this.lunarYear < 2100 && offset >= daysInLunarYear) {
            offset -= daysInLunarYear;
            daysInLunarYear = Lunar.getLunarYearDays(++this.lunarYear);
        }
        // 農曆年數字    

        // 按農曆月遞減每月的農曆天數，確定農曆月份    
        int lunarMonth = 1;
        // 所在農曆年閏哪個月,若沒有返回0    
        int leapMonth = Lunar.getLunarLeapMonth(this.lunarYear);
        // 是否閏年    
        this.isLeapYear = leapMonth > 0;
        // 閏月是否遞減    
        boolean leapDec = false;
        boolean isLeap = false;
        int daysInLunarMonth = 0;
        while (lunarMonth<13 && offset>0) {
            if (isLeap && leapDec) { // 如果是閏年,並且是閏月    
                // 所在農曆年閏月的天數    
                daysInLunarMonth = Lunar.getLunarLeapDays(this.lunarYear);
                leapDec = false;
            } else {
                // 所在農曆年指定月的天數    
                daysInLunarMonth = Lunar.getLunarMonthDays(this.lunarYear, lunarMonth);
            }
            if (offset < daysInLunarMonth) {
                break;
            }
            offset -= daysInLunarMonth;

            if (leapMonth == lunarMonth && isLeap == false) {
                // 下個月是閏月    
                leapDec = true;
                isLeap = true;
            } else {
                // 月份遞增    
                lunarMonth++;
            }
        }
        this.maxDayInMonth = daysInLunarMonth;
        // 農曆月數字    
        this.lunarMonth = lunarMonth;
        // 是否閏月    
        this.isLeap = (lunarMonth == leapMonth && isLeap);
        // 農曆日數字    
        this.lunarDay = (int) offset + 1;
        // 取得干支曆    
        this.getCyclicalData();
    }

    /**
     * 取干支曆 不是歷年，曆月干支，而是中國的從立春節氣開始的節月，是中國的太陽十二宮，陽曆的。
     * @param
     */
    private void getCyclicalData() {
        this.solarYear = this.solar.get(Calendar.YEAR);
        this.solarMonth = this.solar.get(Calendar.MONTH);
        this.solarDay = this.solar.get(Calendar.DAY_OF_MONTH);
        this.hour = this.solar.get(Calendar.HOUR_OF_DAY);
        // 干支曆    
        int cyclicalYear = 0;
        int cyclicalMonth = 0;
        int cyclicalDay = 0;

        // 干支年 1900年立春後為庚子年(60進制36)    
        int term2 = Lunar.getSolarTermDay(solarYear, 2); // 立春日期    
        // 依節氣調整二月分的年柱, 以立春為界    
        if (solarMonth < 1 || (solarMonth == 1 && solarDay < term2)) {
            cyclicalYear = (solarYear - 1900 + 36 - 1) % 60;
        } else {
            cyclicalYear = (solarYear - 1900 + 36) % 60;
        }

        // 干支月 1900年1月小寒以前為 丙子月(60進制12)    
        int firstNode = Lunar.getSolarTermDay(solarYear, solarMonth * 2); // 傳回當月「節」為幾日開始    
        // 依節氣月柱, 以「節」為界    
        if (solarDay < firstNode) {
            cyclicalMonth = ((solarYear - 1900) * 12 + solarMonth + 12) % 60;
        } else {
            cyclicalMonth = ((solarYear - 1900) * 12 + solarMonth + 13) % 60;
        }

        // 當月一日與 1900/1/1 相差天數    
        // 1900/1/1與 1970/1/1 相差25567日, 1900/1/1 日柱為甲戌日(60進制10)    
        cyclicalDay = (int) (Lunar.UTC(solarYear, solarMonth, solarDay, 0, 0, 0) / 86400000 + 25567 + 10) % 60;
        this.cyclicalYear = cyclicalYear;
        this.cyclicalMonth = cyclicalMonth;
        this.cyclicalDay = cyclicalDay;
    }

    /**
     * 取農曆年生肖
     * @return 農曆年生肖(例:龍)
     */
    public String getAnimalString() {
        return Lunar.Animals[(this.lunarYear - 4) % 12];
    }

    /**
     * 返回西曆日期的節氣字串
     * @return 二十四節氣字串,若不是節氣日,返回空串(例:冬至)
     */
    public String getTermString() {
        // 二十四節氣    
        String termString = "";
        if (Lunar.getSolarTermDay(solarYear, solarMonth * 2) == solarDay) {
            termString = Lunar.solarTerm[solarMonth * 2];
        } else if (Lunar.getSolarTermDay(solarYear, solarMonth * 2 + 1) == solarDay) {
            termString = Lunar.solarTerm[solarMonth * 2 + 1];
        }
        return termString;
    }


    /**
     * 取得干支曆字串
     *
     * @return 干支曆字串(例:甲子年甲子月甲子日)
     */
    public String getCyclicalDateString() {
        return this.getCyclicaYear() + "年" + this.getCyclicaMonth() + "月"
                + this.getCyclicaDay() + "日";
    }

    /**
     * 年份天干
     * @return 年份天干
     */
    public int getTiananY() {
        return Lunar.getTianan(this.cyclicalYear);
    }

    /**
     * 月份天干
     * @return 月份天干
     */
    public int getTiananM() {
        return Lunar.getTianan(this.cyclicalMonth);
    }

    /**
     * 日期天干
     * @return 日期天干
     */
    public int getTiananD() {
        return Lunar.getTianan(this.cyclicalDay);
    }

    /**
     * 年份地支
     * @return 年分地支
     */
    public int getDeqiY() {
        return Lunar.getDeqi(this.cyclicalYear);
    }

    /**
     * 月份地支
     * @return 月份地支
     */
    public int getDeqiM() {
        return Lunar.getDeqi(this.cyclicalMonth);
    }

    /**
     * 日期地支
     * @return 日期地支
     */
    public int getDeqiD() {
        return Lunar.getDeqi(this.cyclicalDay);
    }

    /**
     * 時間地支
     * @return
     */
    public int getDeqiH() {
        return Lunar.getHourDeqi(this.hour);
    }

    /**
     * 取得干支年字串
     * @return 干支年字串
     */
    public String getCyclicaYear() {
        return Lunar.getCyclicalString(this.cyclicalYear);
    }

    /**
     * 取得干支月字串
     * @return 干支月字串
     */
    public String getCyclicaMonth() {
        return Lunar.getCyclicalString(this.cyclicalMonth);
    }

    /**
     * 取得干支日字串
     * @return 干支日字串
     */
    public String getCyclicaDay() {
        return Lunar.getCyclicalString(this.cyclicalDay);
    }

    /**
     * 返回農曆日期字串
     * @return 農曆日期字串
     */
    public String getLunarDayString() {
        return Lunar.getLunarDayString(this.lunarDay);
    }

    /**
     * 返回農曆日期字串
     * @return 農曆日期字串
     */
    public String getLunarMonthString() {
        return (this.isLeap() ? "閏" : "") + Lunar.getLunarMonthString(this.lunarMonth);
    }

    /**
     * 返回農曆日期字串
     * @return 農曆日期字串
     */
    public String getLunarYearString() {
        return Lunar.getLunarYearString(this.lunarYear);
    }

    /**
     * 返回農曆表示字串
     * @return 農曆字串(例:甲子年正月初三)
     */
    public String getLunarDateString() {
        return this.getLunarYearString() + "年"
                + this.getLunarMonthString() + "月"
                + this.getLunarDayString() + "日";
    }

    /**
     * 農曆年是否是閏月
     * @return 農曆年是否是閏月
     */
    public boolean isLeap() {
        return isLeap;
    }

    /**
     * 農曆年是否是閏年
     * @return 農曆年是否是閏年
     */
    public boolean isLeapYear() {
        return isLeapYear;
    }

    /**
     * 當前農曆月是否是大月
     * @return 當前農曆月是大月
     */
    public boolean isBigMonth() {
        return this.getMaxDayInMonth()>29;
    }

    /**
     * 當前農曆月有多少天
     * @return 當前農曆月有多少天
     */
    public int getMaxDayInMonth() {
        return this.maxDayInMonth;
    }

    /**
     * 農曆日期
     * @return 農曆日期
     */
    public int getLunarDay() {
        return lunarDay;
    }

    /**
     * 農曆月份
     * @return 農曆月份
     */
    public int getLunarMonth() {
        return lunarMonth;
    }

    /**
     * 農曆年份
     * @return 農曆年份
     */
    public int getLunarYear() {
        return lunarYear;
    }

    /**
     * 西曆日期
     * @return 西曆日期
     */
    public int getSolarDay() {
        return solarDay;
    }

    /**
     * 西曆月份
     * @return 西曆月份 (不是從0算起)
     */
    public int getSolarMonth() {
        return solarMonth+1;
    }

    /**
     * 西曆年份
     * @return 西曆年份
     */
    public int getSolarYear() {
        return solarYear;
    }

    /**
     * 星期幾
     * @return 星期幾(星期日為:1, 星期六為:7)
     */
    public int getDayOfWeek() {
        return this.solar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 黑色星期五
     * @return 是否黑色星期五
     */
    public boolean isBlackFriday() {
        return (this.getSolarDay() == 13 && this.solar.get(Calendar.DAY_OF_WEEK) == 6);
    }

    /**
     * 是否是今日
     * @return 是否是今日
     */
    public boolean isToday() {
        Calendar clr = Calendar.getInstance();
        return clr.get(Calendar.YEAR)==this.solarYear &&
            clr.get(Calendar.MONTH)==this.solarMonth &&
            clr.get(Calendar.DAY_OF_MONTH)==this.solarDay;
    }

    /**
     * 取得西曆節日名稱
     * @return 西曆節日名稱,如果不是節日返回空串
     */
    public String getSFestivalName() {
        return this.sFestivalName;
    }

    /**
     * 取得農曆節日名稱
     * @return 農曆節日名稱,如果不是節日返回空串
     */
    public String getLFestivalName() {
        return this.lFestivalName;
    }

    /**
     * 是否是農曆節日
     * @return 是否是農曆節日
     */
    public boolean isLFestival() {
        if (!this.isFinded) this.findFestival();
        return this.isLFestival;
    }

    /**
     * 是否是西曆節日
     * @return 是否是西曆節日
     */
    public boolean isSFestival() {
        if (!this.isFinded) this.findFestival();
        return this.isSFestival;
    }

    /**
     * 是否是節日
     * @return 是否是節日
     */
    public boolean isFestival() {
        return this.isSFestival() || this.isLFestival();
    }

    /**
     * 是否是放假日
     * @return 是否是放假日
     */
    public boolean isHoliday() {
        if (!this.isFinded) this.findFestival();
        return this.isHoliday;
    }

    /**
     * 其他日期說明
     * @return 日期說明(如:民國2年)
     */
    public String getDescription() {
        if (!this.isFinded) this.findFestival();
        return this.description;
    }

    /**
     * 干支字串
     * @param cyclicalNumber 指定干支位置(數字,0為甲子)
     * @return 干支字串
     */
    private static String getCyclicalString(int cyclicalNumber) {
        return Lunar.Tianan[Lunar.getTianan(cyclicalNumber)] + Lunar.Deqi[Lunar.getDeqi(cyclicalNumber)];
    }

    /**
     * 獲得地支
     * @param cyclicalNumber
     * @return 地支 (數字)
     */
    private static int getDeqi(int cyclicalNumber) {
         return cyclicalNumber % 12;
    }

    /**
     * 獲得天干
     * @param cyclicalNumber
     * @return 天干 (數字)
     */
    private static int getTianan(int cyclicalNumber) {
         return cyclicalNumber % 10;
    }

    private static int getHourDeqi(int hour) {
        int h = 0;
        if (hour == 0 || hour == 23) {
            h = 0;
        } else {
            h = (int) ((hour + 1) / 2);
        }
        return h;
    }

    /**
     * 返回指定數位的農曆年份表示字串
     * @param lunarYear 農曆年份(數字,0為甲子)
     * @return 農曆年份字串
     */
    private static String getLunarYearString(int lunarYear) {
        return Lunar.getCyclicalString(lunarYear - 1900 + 36);
    }

    /**
     * 返回指定數位的農曆月份表示字串
     * @param lunarMonth 農曆月份(數字)
     * @return 農曆月份字串 (例:正)
     */
    private static String getLunarMonthString(int lunarMonth) {
        String lunarMonthString = "";
        if (lunarMonth == 1) {
            lunarMonthString = Lunar.lunarString2[4];
        } else {
            if (lunarMonth > 9)
                lunarMonthString += Lunar.lunarString2[1];
            if (lunarMonth % 10 > 0)
                lunarMonthString += Lunar.lunarString1[lunarMonth % 10];
        }
        return lunarMonthString;
    }

    /**
     * 返回指定數位的農曆日表示字串
     * @param lunarDay 農曆日(數字)
     * @return 農曆日字串 (例: 廿一)
     */
    private static String getLunarDayString(int lunarDay) {
        if (lunarDay<1 || lunarDay>30) return "";
        int i1 = lunarDay / 10;
        int i2 = lunarDay % 10;
        String c1 = Lunar.lunarString2[i1];
        String c2 = Lunar.lunarString1[i2];
        if (lunarDay < 11) c1 = Lunar.lunarString2[0];
        if (i2 == 0) c2 = Lunar.lunarString2[1];
        return c1 + c2;
    }

    //*************************************************************

    public int iSolarday(){
    	int i = Lunar.getSolarTermDay(solarYear, solarMonth * 2);
    	return i ;
    }

    public String[] getBaZiString() {
        String[] bazi = new String[]{"年天","月天","日天","時天","年地","月地","日地","時地"};

        bazi[0] = Tianan[getTiananY()];
        bazi[1] = Tianan[getTiananM()];
        bazi[2] = Tianan[getTiananD()];
        bazi[4] = Deqi[getDeqiY()];
        bazi[5] = Deqi[getDeqiM()];
        bazi[6] = Deqi[getDeqiD()];
        bazi[7] = Deqi[getDeqiH()];
        bazi[3] = Tianan[getTiananH(getTiananD(), getDeqiH())];
        return bazi;
    }

    public int[] getBaZiInt() {
        int[] bazi = new int[8];

        bazi[0] = getTiananY();
        bazi[1] = getTiananM();
        bazi[2] = getTiananD();
        bazi[4] = getDeqiY();
        bazi[5] = getDeqiM();
        bazi[6] = getDeqiD();
        bazi[7] = getDeqiH();
        bazi[3] = getTiananH(getTiananD(), getDeqiH());
        return bazi;
    }

    /**
     * 計算時天干
     * @param D 時地支
     * @param T 日天干
     * @return
     */
    private int getTiananH(int D, int T) {
        int A = 0, B = 0, C = 0;

        A = D;
        if (T < 5) {
            B = T * 2;
        } else {
            B = T * 2 - 10;
        }

        C = A + B;
        C = C % 10;

        return C;
    }
}  
