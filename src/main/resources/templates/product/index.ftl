<!DOCTYPE html>
<html lang="en">
    <#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "../common/nav.ftl">

    <#--主要内容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <form role="form" method="post" action="/sell/seller/product/save">
                        <div class="form-group">
                            <label for="productName">名称</label>
                            <input type="text" class="form-control" id="productName" name="productName" value="${(product.productName) !''}"/>
                        </div>
                        <div class="form-group">
                            <label for="productPrice">价格</label>
                            <input type="text" class="form-control" id="productPrice" name="productPrice" value="${(product.productPrice) !''}"/>
                        </div>
                        <div class="form-group">
                            <label for="productStock">库存</label>
                            <input type="number" class="form-control" id="productStock" name="productStock" value="${(product.productStock) !''}"/>
                        </div>
                        <div class="form-group">
                            <label for="productDescription">描述</label>
                            <input type="text" class="form-control" id="productDescription" name="productDescription" value="${(product.productDescription) !''}"/>
                        </div>
                        <div class="form-group">
                            <label for="productIcon">图片</label>
                            <img height="120" width="140" src="${(product.productIcon)!''}">
                            <input type="text" class="form-control" id="productIcon" name="productIcon" value="${(product.productIcon)!''}"/>
                        </div>
                        <div class="form-group">
                            <label for="categoryType">类目</label>
                            <select name="categoryType" id="categoryType" class="form-control">
                                <#list categoryList as category>
                                    <option value="${category.categoryType}"
                                            <#if (product.categoryType) ?? && product.categoryType == category.categoryType >
                                                selected
                                            </#if>
                                    >${category.categoryName}</option>
                                </#list>
                            </select>
                        </div>
                        <input type="hidden" class="form-control" name="productId" value="${(product.productId)!''}"/>
                        <button type="submit" class="btn btn-default">提交</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>