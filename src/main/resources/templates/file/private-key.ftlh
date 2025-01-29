<#import "../layout.ftlh" as main>
<@main.layout ; spring>
    <div class="container mt-5">
        <div class="card shadow-sm p-4" style="width: 50%; margin-left: 300px">
            <h3 class="text-center">Your download key</h3>
            <p class="text-muted text-center">Copy the link and share it if you want to provide access.</p>

            <div class="alert alert-light border d-flex justify-content-between align-items-center">
                <span id="downloadLink">http://localhost:8081/download/${privateKey}/${fileId}</span>
            </div>

            <div class="text-center mt-3">
                <a href="/profile" class="btn btn-secondary">Return to profile</a>
            </div>
        </div>
    </div>

</@main.layout>