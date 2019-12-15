
<ul class="article__toc">
    <#list article.articleToC as item>
        <li class="${item.className}">
            <a href="#${item.id}">${item.text}</a>
        </li>
    </#list>
</ul>