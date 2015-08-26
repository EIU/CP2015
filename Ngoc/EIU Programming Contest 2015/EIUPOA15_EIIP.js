registerProblem('EIUPOA15_EIIP',
    [{
        input: '3\n\
                194.0.160.177\n\
                194.0.160.183\n\
                194.0.160.178',
        output: '194.0.160.176\n\
                255.255.255.248'
    }
    ],
    function () {

        var n = +readline();

        function readIp() {
            var ipParts = readline().split('.');
            return (+ipParts[0] << 24) | (+ipParts[1] << 16) | (+ipParts[2] << 8) | (+ipParts[3]);
        }

        var ips = [];
        for (var i = 0; i < n; i++) {
            ips.push(readIp());
        }

        var flag = 0x80000000;
        var subnet = 0x00000000;
        for (var i = 32; i > 0; i--) {
            var biti = ips[0] & flag;
            var j = 1;
            while (j < ips.length && biti == (ips[j] & flag)) {
                j++;
            }
            if (j == ips.length) {
                subnet |= flag;
                flag >>= 1;
            }
            else {
                break;
            }
        }

        function toIpText(ip) {
            return (((ip >> 24) + 256) % 256) + '.' + ((ip >> 16) & 0xff) + '.' + ((ip >> 8) & 0xff) + '.' + (ip & 0xff);
        }

        print(toIpText(ips[0] & subnet));
        print(toIpText(subnet));
    }
);

/*
Link: http://www.spoj.com/EIUPOA15/problems/EIUGAME/
*/