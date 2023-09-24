<script>

    export let data;
    let onRender = false;
    $: id = data.id;
    let categories = data.categories;
    $: subcategories = update(id);

    function update(id){
        onRender = false;
        let res = [];
        for(let subcategory of data.subcategories){
            if(id == subcategory.category){
                res.push(subcategory);
            }
        }

        onRender = true;
        return res;
    }
</script>

<div>
    {#if onRender}
    <div class="mt-4 pt-4 h-full bg-slate-600 rounded-2xl">
        <div class="grid grid-cols-6 pl-4 pr-4 pb-4">
            {#each categories as category}
                <div class="flex rounded-xl text-sm mr-2 py-2 justify-center align-middle {category.id == id ? 'bg-orange-400' : 'bg-slate-700'}">
                    <a class="{category.id == id ? "text-slate-700": "text-slate-200"}" href="/freelance/category/{category.id}">
                        {category.name}
                    </a>
                </div>
            {/each}
        </div>
    </div>
    <div class="py-2 grid grid-cols-5 bg-slate-600 mt-2 rounded-2xl">
        {#each subcategories as subcategory}
            <div class="flex justify-center m-2 align-middle bg-slate-700 rounded-xl">
                <a class="flex flex-row self-center text-sm p-2 mx-2 justify-center text-slate-200" href="/freelance/subcategory/{subcategory.id}">
                    {subcategory.name}
                </a>
            </div>
        {/each}
    </div>
    {/if}
</div>
