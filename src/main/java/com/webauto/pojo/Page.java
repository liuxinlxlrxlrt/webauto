package com.webauto.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Page类型，一个page一个页面
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Page {

    private String keyword;
    private List<UIElement> uiElements;


}
