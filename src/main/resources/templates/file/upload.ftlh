<#import "../layout.ftlh" as main>
<@main.layout ; spring>
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm p-4">
                <h3 class="text-center mb-4">Upload File</h3>
                <form action="/files/upload" method="post" enctype="multipart/form-data">
                    <#if _csrf??>
                        <input type="hidden" name="${(_csrf.parameterName)!'csrf-param-name'}"
                               value="${(_csrf.token)!'csrf-token'}"/>
                    </#if>
                    <div class="mb-3">
                        <label for="file" class="form-label">Choose file</label>
                        <input type="file" class="form-control" id="file" name="file" required>
                    </div>

                    <div class="mb-3">
                        <label for="status" class="form-label">Status:</label>
                        <select class="form-select" id="status" name="status" required>
                            <option value="public">Public</option>
                            <option value="private">Private</option>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="category" class="form-label text-dark">Категория:</label>
                        <select class="form-select form-control form-control-lg" id="category" name="categoryId" required>
                            <#list categories as category>
                                <option value="${category.id}">${category.name}</option>
                            </#list>
                        </select>
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-primary w-100">Upload</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</@main.layout>