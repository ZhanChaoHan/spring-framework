package com.jachs.spring_transactional.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * @author zhanchaohan
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Software {
    private String softWareId;
    private String cId;
    private String softWareName;
}
