


/* renamed from: NPC  reason: default package */
/* loaded from: a.jar:NPC.class */
public class NPC {
    private int No;
    private int lifePoints;
    private int operation;
    public static String[] WhiteForestNPC = {"波波", "刺尾虫", "鬼斯", "毽子草", "电击怪", "波克比", "腕力", "懒人翁", "小猫怪", "小鸭嘴龙", "小磁怪", "拉鲁拉丝", "姆克儿", "露力丽", "含羞苞", "走路草", "咩利羊", "可可多拉", "喇叭芽", "橡实果", "铁甲犀牛", "莲叶童子", "大颚蚁", "凯西", "尼多朗", "尼多兰", "咕妞妞", "3D龙", "好运蛋", "宝贝龙"};

    public NPC(int no, int hp, int op) {
        this.No = no;
        this.lifePoints = hp;
        this.operation = op;
    }

    public int getNo() {
        return this.No;
    }

    public int getLp() {
        return this.lifePoints;
    }

    public int getOp() {
        return this.operation;
    }
}
