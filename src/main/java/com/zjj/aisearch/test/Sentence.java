package com.zjj.aisearch.test;

public class Sentence {
    private String text = "";
    private int startOffset = 0;
    private int endOffset = 0;

    public Sentence(String text) {
        this.text = text;
    }
    public String next() {
        boolean start = true;
        for (int i = startOffset; i < text.length(); i++) {
            int type = Character.getType(text.charAt(i));
            switch (type) {
                case Character.UPPERCASE_LETTER:
                case Character.LOWERCASE_LETTER:
                case Character.TITLECASE_LETTER:
                case Character.MODIFIER_LETTER:
                case Character.OTHER_LETTER:
                    /*
                     * 1. 0x3041-0x30f6 -> ぁ-ヶ //日文(平|片)假名
                     * 2. 0x3105-0x3129 -> ㄅ-ㄩ
                     * //注意符号
                     */
                    if (start) {
                        startOffset = i;
                        start = false;
                    }
                    break;
                case Character.LETTER_NUMBER:
// ⅠⅡⅢ 单分 
                case Character.OTHER_NUMBER:
// ①⑩㈠㈩⒈⒑⒒⒛⑴⑽⑾⒇ 连着用 
                default:
// 其它认为无效字符 
                    if (!start) {
                        endOffset = i;
                        i = text.length();
                    }
            }// switch
        }
        String word = "";
        if (startOffset < text.length()) {
            if (endOffset <= startOffset) {
                endOffset = text.length() - 1;
            }
            word = text.substring(startOffset, endOffset);
            startOffset = endOffset + 1;
        }
        return word;
    }


    public static void main(String[] args) {
        Sentence sen = new Sentence("皇帝那所谓的至高无上的权力在文官集团的大爷们眼中也算不得什么，骂你，讽刺你，那是为了国家大事，那是忠言逆耳，你能说他不对吗？\n" +
                "\n" +
                "而且这些大爷们既不能杀，也不能轻易打，杀了他们，公务你自己一个人能干吗？\n" +
                "\n" +
                "劳动模范朱元璋老先生自然可以站出来说：把他们都杀光，我能干！\n" +
                "\n" +
                "可是朱瞻基不能这样说。\n" +
                "\n" +
                "于是在太祖皇帝死去二十年后，绳子失去了平衡，获得了票拟权的内阁集团变得更强大，皇帝一个人就要支撑不住了。这样下去，他将被大臣们任意摆布。\n" +
                "\n" +
                "苦苦支撑的朱瞻基一步步地被拉了过去，正在这时，他看见旁边站着一个人，于是他对这个人说：“你来，和我一起拔！”\n" +
                "\n" +
                "从此这个人就参加了拔河，并成为这场游戏的一个重要组成部分。");
        String w = "";
        while (!(w = sen.next()).isEmpty()) {
            System.out.println(w);
        }
    }
}