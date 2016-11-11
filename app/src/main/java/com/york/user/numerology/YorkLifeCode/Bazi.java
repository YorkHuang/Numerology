package com.york.user.numerology.YorkLifeCode;

/**
 * Created by GRT-EK on 2016/11/9.
 */

public class Bazi {

    private int[] mLifeCode;

    public Bazi() {

    }

    public Bazi(int[]  lifeCode) {
        this.mLifeCode = lifeCode;
    }

    /**
     * 取得人元
     * @param singleDeqi
     * @return
     */
    public int[] getElement(int singleDeqi){

        int[][] element = new int[][] { { 9 }, { 5, 9, 7 }, { 0, 2, 4 },
                                        { 1 }, { 4, 1, 9 }, { 2, 6, 4 },
                                        { 3, 5 }, { 5, 3, 1 }, { 6, 8, 4 },
                                        { 7 }, { 4, 7, 3 }, { 8, 0 }};

        return element[singleDeqi];
    }

    /**
     * 取得五行的百分比
     */
    public void getWuSinPercentage() {

        // 木=0 ,火=1 ,土=2 ,金=3 ,水=4
        double[] iWuSin = new double[] { 0, 0, 0, 0, 0 };
        int[] WuSin_TanGan = new int[] { 0, 0, 1, 1, 2, 2, 3, 3, 4, 4 };

        for (int i=0 ; i<4 ; i++) {
            iWuSin[WuSin_TanGan[mLifeCode[i]]]++;
        }

        for (int i=4 ; i<8 ; i++) {
            int[] element = getElement(this.mLifeCode[i]);
            for (int j=0; j<element.length; j++) {
                iWuSin[WuSin_TanGan[element[j]]]++;
            }
        }

        35  35  35  35
        100 100 200 100

    }


}
