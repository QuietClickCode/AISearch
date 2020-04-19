package com.zjj.aisearch.vim;

/**
 * @program: AISearch
 * @description: vim实用快捷键
 * @author: zjj
 * @create: 2020-04-15 20:58:57
 **/
public class VimDemo {
    /**
     * 删除操作:
     *      字符删除:
     * x 删除光标所在处字符 这是向前删除 xxxxxxxxxx aaaaaaaaa acc
     * X 删除光标所在处字符 这是向后删除 xxxxxxxxxx aacc
     *
     * 单词删除
     * dw 删除到下一个单词开头 这是删除光标所在处到下一个单词的第一个字母     i am a programmer what are you job
     * de 删除到本单词末尾 包括光标所在处  e 是往后走到本单词末尾     i am a programmer what are you job
     * dE 删除到本单词末尾 包括标点 不包括光标所在处  i am a programmer what are you job;
     * db 删除到前一个单词 i am a programmer what are you
     * dB 删除到前一个单词包括标点在内 i am a programmer;what are you
     *
     * e 往前删除 b 往后删除 e b 会忽略标点 E B 不会
     *
     * 行删除
     * dd 删除一整行
     * fsajkjaslksajfk fkalsjfklsajf; sfadj;lsajf;
     * fsajkjaslksajfk fkalsjfklsajf; sfadj;lsajf;
     * fsajkjaslksajfk fkalsjfklsajf; sfadj;lsajf;
     *
     * D 或 d$  删除光标位置到本行末尾
     * fjkalsjfk jfklasjfk jfkalsjjkl asklfjskal
     *
     * d^ 删除到本行开头
     * fkals fjaskl jfaskl jklas kljlasjklf
     *
     *  dh dl  向前/向后删除 发送框架 jfaksjfklasj fjasklfjas jks
     *
     *
     *  删除命令 需要配合移动命令才能发挥最大的作用
     *
     *  3dd 代表删除3行
     *  fjsadkjfasjkl
     *  jfkasjfkl
     *  jfklasjfasdjklf
     *
     *
     *  d2w daw
     *
     *  删除 从光标后的两个单词 和删除 整个单词
     *  fjsakl fklasjfkls fjklasjlfs afjkladsjfajsk
     *
     *  jj
     *
     *  d0 d^  删除到开头
     *
     *  . 重复上次修
     *
     *  DD 和 .可以配合
     *
     *  fjskalfjkads
     *
     *  jfkas
     *  jfkasl
     *  jfkadjf
     *  fjklasfad
     *  jfklasdjfkads
     *  jfklasjfads;;;;;;;;;;;;;;;;;;;;;;;;
     *
     *  A $ 移动到行尾  一个变输入模式,一个不变
     *  0 ^ 移动到行首 一个真正的行首 一个字符行首
     *   enter o  换行  一个不进输入模式,一个进输入模式
     *   gg 整个文件头部
     *   G 整个文件尾部
     *
     *   跳转到指定行
     *   3gg 3G 或者  :23
     *
     *      ffajkasjfkas fsjadklfjkadsluioquwrinvas fsdajklwiojrfi fiosnvxkiowf
     *
     *
     *      w b 移动到下一个车单词开头 上一个单词开头
     *      jfkas faklsjflkas fjklas
     *
     * u 撤销 ctrl + R 反撤销 就疯狂撒
     * fsfjwwjk fsaklj fsakfj
     *
     * v V 进选择模式
     * vc s 选中当前字符并更改
     * vd x 效果一样 都是删除一个字符
     * kjajflskk kkkksssssask
     * x 剪贴
     * kkfklas ffjkdsasfjas
     *
     * kk
     * kkk
     * :
     *
     * diw daw  删除
    w   * fsa  sdkfjsaklh
     *
     * dtw dfw dFw
     * 删除光标到行内目标具体位置
js sfadjqojfsa
     *
     *kk
     *
     * 以上可以换c 代替 d,区c会进入插入模式jhh
     * S o
     * S 删除一行S
     *
     * di ( 删除匹配的
     * da ( 包含 匹配符号
     * ()
     * "fasfsa fas(fsa (fjak fjask)) "
     *
     *
     * :form,to d
     *
     * 删除 指定行 * * *
     *
     *
     *
     * J把下一行合并到当前行
     * jfkalsj
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}
