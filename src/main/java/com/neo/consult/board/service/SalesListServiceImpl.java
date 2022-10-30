package com.neo.consult.board.service;

import com.neo.common.vo.BoardVO;
import com.neo.common.vo.MasterKeyVO;
import com.neo.mappers.BoardMapper;
import com.neo.mappers.CommonMapper;
import com.neo.util.UtilCommon;
import com.neo.util.UtilJsonResult;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Service("salesListService")
@Transactional
public class SalesListServiceImpl implements SalesListService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MessageSource messageSource;
    @Autowired
    LocaleResolver localeResolver;
    @Resource(name = "boardMapper")
    private BoardMapper boardMapper;
    @Resource(name = "commonMapper")
    private CommonMapper commonMapper;

    @Override
    public int salesListCount(BoardVO paramVO) throws Exception {
        return boardMapper.boardListCount(paramVO);
    }

    @Override
    public int salesListUpdateViewCnt(BoardVO paramVO) throws Exception {
        return boardMapper.boardUpdateViewCnt(paramVO);
    }

    @Override
    public List<BoardVO> salesList(BoardVO paramVO) throws Exception {
        List<BoardVO> resultList = new ArrayList<>();
        resultList = boardMapper.boardList(paramVO);
        return resultList;
    }

    @Override
    public BoardVO salesListDetail(BoardVO paramVO) throws Exception {
        BoardVO result = new BoardVO();
        result = boardMapper.boardDetail(paramVO);

        // 에디터데이타는 html 이므로 unescape 한다
        result.setCONTENT(StringEscapeUtils.unescapeHtml4(result.getCONTENT()));

        return result;
    }

    // 프론트에서 사용
    @Override
    public BoardVO salesListDetailPre(BoardVO paramVO) throws Exception {
        BoardVO result = new BoardVO();
        result = boardMapper.boardDetailPre(paramVO);

        return result;
    }

    // 프론트에서 사용
    @Override
    public BoardVO salesListDetailNext(BoardVO paramVO) throws Exception {
        BoardVO result = new BoardVO();
        result = boardMapper.boardDetailNext(paramVO);

        return result;
    }

    @Override
    public JSONObject salesListInsert(BoardVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        UtilJsonResult.setReturnCodeFail(json);
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

        // S:유효성체크
        // 이름 체크
        if (UtilCommon.isEmpty(paramVO.getTITLE())) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[]{"뉴스제목"}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(json, rMsg);
            return json;

        }
        // E:유효성체크

        // S: 코드 생성
        String masterKey = "";
        MasterKeyVO masterKeyVO = new MasterKeyVO();
        masterKeyVO.setKEY_GBN("BOARD_CODE");
        masterKey = commonMapper.getFnGetMasterKey(masterKeyVO);
        paramVO.setBOARD_CODE(masterKey);
        // E: 코드 생성

        // 글등록
        paramVO.setIN_USER(paramVO.getLoginCode());
        paramVO.setUP_USER(paramVO.getLoginCode());
        paramVO.setBOARD_GUBUN("MC0000500005");
        boardMapper.boardInsert(paramVO);

        UtilJsonResult.setReturnCodeSuc(json, rMsg);

        return json;
    }

    @Override
    public JSONObject salesListUpdate(BoardVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        UtilJsonResult.setReturnCodeFail(json);
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

        // S:유효성체크
        if (UtilCommon.isEmpty(paramVO.getTITLE())) {
            rMsg = messageSource.getMessage("validation.empty.input", new String[]{"영업현황 수정"}, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(json, rMsg);
            return json;
        }
        if (UtilCommon.isEmpty(boardMapper.boardDetail(paramVO))) {
            rMsg = messageSource.getMessage("validation.update.empty.object", null, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(json, rMsg);
            return json;
        }
        // E:유효성체크
        paramVO.setUP_USER(paramVO.getLoginCode());
        boardMapper.boardUpdate(paramVO);

        UtilJsonResult.setReturnCodeSuc(json);

        return json;
    }

    @Override
    public JSONObject salesListDelete(BoardVO paramVO, HttpServletRequest request) throws Exception {
        JSONObject json = new JSONObject();
        UtilJsonResult.setReturnCodeFail(json);
        String rMsg = messageSource.getMessage("error.system.default", null, localeResolver.resolveLocale(request));

        // S:유효성체크
        if (UtilCommon.isEmpty(boardMapper.boardDetail(paramVO))) {
            rMsg = messageSource.getMessage("validation.delete.empty.object", null, localeResolver.resolveLocale(request));
            UtilJsonResult.setReturnCodeFail(json, rMsg);
            return json;
        }
        // E:유효성체크

        paramVO.setDEL_YN("Y");
        paramVO.setUP_USER(paramVO.getLoginCode());
        boardMapper.boardDelete(paramVO);

        UtilJsonResult.setReturnCodeSuc(json);

        return json;
    }

}
