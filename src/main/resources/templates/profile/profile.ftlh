<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container mt-4">
        <h1 class="text-center mb-4">${user.username}</h1>
        <h4 class="text-center mb-4">My files</h4>

        <#if user.files?size gt 0>
            <div class="row">
                <#list user.files as file>
                    <div class="col-md-4 col-sm-6 mb-3">
                        <div class="card shadow-sm">
                            <div class="card-body">
                                <h5 class="card-title">${file.getOriginalFilename()}</h5>
                                <p class="card-text"><strong>Category:</strong> ${file.getCategory()}</p>
                                <p class="card-text"><strong>Downloads:</strong> ${file.getDownloadCounter()}</p>
                                <p class="card-text"><strong>Status:</strong> ${file.status}</p>
                                <a href="/download/${file.id}" class="btn btn-primary w-50">Download</a>
                            </div>
                        </div>
                    </div>
                </#list>
            </div>


        <#else >
            Files not found
        </#if>
    </div>
</@main.layout>