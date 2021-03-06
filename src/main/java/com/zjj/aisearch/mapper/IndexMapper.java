package com.zjj.aisearch.mapper;


import com.zjj.aisearch.model.*;

import java.util.List;
import java.util.Map;

public interface IndexMapper  {
    User index();
    int insertSearchRecord(SearchRecord searchRecord);

    Article search(int keyword);
    List<Item> searchItem(String keyword);
    List<JianShuArticle> searchJianShuArticle(String keyword);

    int insertBrowserInfo(BrowserInfo browserInfo);

    int insertLocation(Location location);


    int insertAiNote(AiNote aiNote);


    User selectUserByUserName(String username);

    int insertUser(User user);

    List<UserLocation> selectUserLocation();

    int insertLoginLog(LoginLog loginLog);

    List<LoginLogLocation> selectLoginLogLocation();
    List<LogoutLogList> selectLogoutLogList();

    int insertLogoutLog(LogoutLog logoutLog);


    int insertSystemLog(SystemLog systemLog);

    Integer selectSystemLogList();


    List<ZhiHuArticle> searchZhiHuArticle(String title);

    List<AiNoteList> selectAiNoteList();

    List<SearchRecordList> selectSearchRecordList();

    List<EditorList> selectEditorList();

    List<MarkDownList> selectMarkDownList();

    int validateUsername(String username);

    String selectPermission(Integer userId);

    List<Article> queryArticle(Map<String, String> map);

    Integer selectainotelistlength();

    Integer selecteditorlistlength();

    Integer selectlistlength();

}
