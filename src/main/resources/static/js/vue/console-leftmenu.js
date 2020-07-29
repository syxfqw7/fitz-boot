Vue.component('console-leftmenu', {
    props: ['isCollapse2'],
    data: function () {
        return {
            count: 0,
            isCollapse3: this.isCollapse2
        }
    },
    watch: {
        isCollapse2: function (val, oldVal) {
            console.log('isCollapse2 new: %s, old: %s', val, oldVal)
        },
        isCollapse3: function (val, oldVal) {
            console.log('isCollapse3 new: %s, old: %s', val, oldVal)
        }
    },
    template: '<el-menu :default-openeds="[\'1\']" :collapse="isCollapse2" style="height: 100%" :collapse-transition="false">\n' +
        '                          <el-submenu index="1">\n' +
        '                              <template slot="title">\n' +
        '                                  <i class="el-icon-menu"></i>\n' +
        '                                  <span slot="title">导航一</span>\n' +
        '                              </template>\n' +
        '                              <el-menu-item-group>\n' +
        '                                  <template slot="title">分组一</template>\n' +
        '                                  <el-menu-item index="1-1">选项1</el-menu-item>\n' +
        '                                  <el-menu-item index="1-2">选项2</el-menu-item>\n' +
        '                              </el-menu-item-group>\n' +
        '                              <el-menu-item-group title="分组2">\n' +
        '                                  <el-menu-item index="1-3">选项3</el-menu-item>\n' +
        '                              </el-menu-item-group>\n' +
        '                              <el-submenu index="1-4">\n' +
        '                                  <template slot="title">选项4</template>\n' +
        '                                  <el-menu-item index="1-4-1">选项4-1</el-menu-item>\n' +
        '                              </el-submenu>\n' +
        '                          </el-submenu>\n' +
        '                          <el-submenu index="2">\n' +
        '                              <template slot="title"><i class="el-icon-menu"></i>\n' +
        '                                  <span slot="title">导航二</span>\n' +
        '                              </template>\n' +
        '                              <el-menu-item-group>\n' +
        '                                  <template slot="title">分组一</template>\n' +
        '                                  <el-menu-item index="2-1">选项1</el-menu-item>\n' +
        '                                  <el-menu-item index="2-2">选项2</el-menu-item>\n' +
        '                              </el-menu-item-group>\n' +
        '                              <el-menu-item-group title="分组2">\n' +
        '                                  <el-menu-item index="2-3">选项3</el-menu-item>\n' +
        '                              </el-menu-item-group>\n' +
        '                              <el-submenu index="2-4">\n' +
        '                                  <template slot="title">选项4</template>\n' +
        '                                  <el-menu-item index="2-4-1">选项4-1</el-menu-item>\n' +
        '                              </el-submenu>\n' +
        '                          </el-submenu>\n' +
        '                          <el-submenu index="3">\n' +
        '                              <template slot="title"><i class="el-icon-setting"></i>\n' +
        '                                  <span slot="title">设置</span>\n' +
        '                              </template>\n' +
        '                              <el-menu-item-group>\n' +
        '                                  <template slot="title">分组一</template>\n' +
        '                                  <el-menu-item index="3-1">选项1</el-menu-item>\n' +
        '                                  <el-menu-item index="3-2">选项2</el-menu-item>\n' +
        '                              </el-menu-item-group>\n' +
        '                              <el-menu-item-group title="分组2">\n' +
        '                                  <el-menu-item index="3-3">选项3</el-menu-item>\n' +
        '                              </el-menu-item-group>\n' +
        '                              <el-submenu index="3-4">\n' +
        '                                  <template slot="title">选项4</template>\n' +
        '                                  <el-menu-item index="3-4-1">选项4-1</el-menu-item>\n' +
        '                              </el-submenu>\n' +
        '                          </el-submenu>\n' +
        '                      </el-menu>'
});