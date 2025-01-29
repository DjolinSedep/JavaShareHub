<#import "../layout.ftlh" as main>
<@main.layout ; spring>
<div class="container mt-4">
    <h2 class="text-center mb-4">Files list</h2>

    <#if files.content?size gt 0>
        <div class="row">
            <#list files.content as file>
                <div class="col-md-4 col-sm-6 mb-3">
                    <div class="card shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">${file.getOriginalFilename()}</h5>
                            <p class="card-text"><strong>Category:</strong> ${file.getCategory()}</p>
                            <p class="card-text"><strong>Downloads:</strong> ${file.getDownloadCounter()}</p>
                            <p class="card-text"><strong>Author:</strong> ${file.getAuthor()}</p>
                            <a href="/download/${file.id}" class="btn btn-primary w-50">Download</a>
                        </div>
                    </div>
                </div>
            </#list>
        </div>

        <#if files.totalPages gt 1>
            <nav aria-label="Page navigation example">
                <ul class="pagination justify-content-center">
                    <#if files.pageable.pageNumber gt 0>
                        <li class="page-item">
                            <a class="page-link" href="/?page=${files.pageable.pageNumber - 1}" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </#if>

                    <#list 0..(files.totalPages - 1) as page>
                        <li class="page-item <#if page == files.pageable.pageNumber>active</#if>">
                            <a class="page-link" href="/?page=${page}">${page + 1}</a>
                        </li>
                    </#list>

                    <#if files.pageable.pageNumber < (files.totalPages - 1)>
                        <li class="page-item">
                            <a class="page-link" href="/?page=${files.pageable.pageNumber + 1}" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                    </#if>
                </ul>
            </nav>
        </#if>

        <#else >
        Files not found
    </#if>
</div>
</@main.layout>