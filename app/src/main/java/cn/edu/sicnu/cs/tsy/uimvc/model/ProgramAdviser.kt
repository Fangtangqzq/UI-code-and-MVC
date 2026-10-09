package cn.edu.sicnu.cs.tsy.uimvc.model

/**
 * MVC 中的 Model 层：持有「下拉框选项 -> 对应语言」的映射，并对外提供查询接口。
 *
 * 为避免在代码中出现字符串字面量，数据由 Controller 从字符串资源读取后注入。
 */
class ProgramAdviser(private val languageByOption: Map<String, String>) {

    /** 根据下拉框选项返回对应的语言；没有对应项时返回 null。 */
    fun getAdvice(option: String): String? = languageByOption[option]
}